package com.vin.controller;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.vin.entity.Author;
import com.vin.entity.Course;
import com.vin.entity.Metric;
import com.vin.entity.Recommend;
import com.vin.entity.User;
import com.vin.entity.UserCourse;
import com.vin.service.CourseService;
import com.vin.service.MetricService;
import com.vin.service.RecommendService;
import com.vin.service.UserCourseService;
import com.vin.service.UserService;

@Component
@Async
public class AsyncCalculateTask {
	@Resource
	private UserCourseService userCourseService;

	@Resource
	private CourseService courseService;

	@Resource
	private MetricService metricService;

	@Resource
	private UserService userService;

	@Resource
	private RecommendService recommendService;

	/**  metric info with weitht. user focus on metric eg: author 60, cataloge 30 */
	HashMap<String, Double> metricMap = new HashMap<String, Double>();
	
	HashMap<String, HashMap<String, Double>> userCourseMetricsMap = new HashMap<String, HashMap<String, Double>>();

	HashMap<String, String> userScourseMap = new HashMap<String, String>(); // entry(userId_sourceId,"")

	HashMap<String, ArrayList<Recommend>> recommendProduct = new HashMap<String, ArrayList<Recommend>>();

	public AsyncCalculateTask() {
	}
	/**
	 * Use the user and the selected course to initiate the user favorite metric.
	 * eg. user_author, user_cataloge, user_length. 
	 * and save these metric and favorites in the userCourseMetricsMap. 
	 * @return
	 */
	public Future<String> initUserCourseMetrics(){
		initMetrics();
		long begin = System.currentTimeMillis();
		List<UserCourse> allUserList = userCourseService.fillAll();
		for (UserCourse uc : allUserList) {
			long userId = uc.getUserId();
			long courseId = uc.getCourseId();
			userScourseMap.put(userId + "_" + courseId, "");
			Course course = courseService.findCourseById(courseId).get();
			Class clazz = course.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String key = field.getName();
				if (metricMap.containsKey(key)) {
					String metricKey = "user_" + key;
					try {
						PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
						Method method = descriptor.getReadMethod();
						Object value = method.invoke(course);
						if (!userCourseMetricsMap.containsKey(metricKey)) {
							userCourseMetricsMap.put(metricKey, new HashMap<String, Double>());
						}
						if (value instanceof Author) {
							userCourseMetricsMap.get(metricKey).put(userId + "_" + ((Author) value).getId(), metricMap.get(key));
						} else {
							userCourseMetricsMap.get(metricKey).put(userId + "_" + value, metricMap.get(key));
						}
						System.out.println(metricKey+" : "+ key + " : " + value +" : "+metricMap.get(key));
					} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException| InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
         
        long end = System.currentTimeMillis();
        System.out.println("========================>>>>>>initUserCourseMetrics time cost ="+(end-begin)+" ms.");
        return new AsyncResult<String>("initUserCourseMetrics");
	}
	 
	 
	/**
	 * load metrics. metric with score.
	 */
	private void initMetrics() {
		List<Metric> metricL = metricService.findAll();
		 //category,author,length
		for(Metric mc:metricL) {
			metricMap.put(mc.getName(),Double.valueOf(mc.getWeight()));
		}
	}
	    
	 /**
	  * calculate all course for each user.
	  * @return
	  * @throws InterruptedException
	  */
	public Future<String> calculateCourse(){
		
		long begin = System.currentTimeMillis();
		List<Course> allCourseList = courseService.findAll();
		List<User> allUserList = userService.findAll();
		
		for (User user : allUserList) {
			calculateUserCourseRecommendList(allCourseList, user.getId());
		}

		// clean all the recommend list at DB.
		recommendService.deleteAll();

		// save calculate results into DB.
		for (Entry<String, ArrayList<Recommend>> entry : recommendProduct.entrySet()) {
			recommendService.save(entry.getValue());
		}

		long end = System.currentTimeMillis();
		System.out.println("========================>>>>>>calculateCourse for all users time cost = " + (end - begin)+" ms.");
		return new AsyncResult<String>("calculateCourse");
	}
	 
	/**
	 * use the user metric score, user favorite metrics, loop all the courses and calculate every course score for the given user. 
	 * @param allCourseList
	 * @param user
	 */
	private void calculateUserCourseRecommendList(List<Course> allCourseList, Long userId) {
		
		double score;
		for(Course course:allCourseList) {
			score = 0.0;
			Class clazz = course.getClass();
			Field[] fields = clazz.getDeclaredFields();
			Boolean isExist = false;
			for (Field field : fields) {
		        String key = field.getName();
		        if(metricMap.containsKey(key)) {
		        	String metricKey = "user_"+key;
					try {
						PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
						Method method = descriptor.getReadMethod();
						Object value = method.invoke(course);
						if(value instanceof Author) {
							Author au = (Author)value;
							if(userCourseMetricsMap.containsKey(metricKey)) {
								if(userCourseMetricsMap.get(metricKey).containsKey(userId+"_"+au.getId())) {
									score = score + userCourseMetricsMap.get(metricKey).get(userId+"_"+au.getId());
									isExist = true;
								}
							}
						}else {
							if(userCourseMetricsMap.containsKey(metricKey)) {
								if(userCourseMetricsMap.get(metricKey).containsKey(userId+"_"+value)) {
									score = score + userCourseMetricsMap.get(metricKey).get(userId+"_"+value);
									isExist = true;
								}
							}
						}
					} catch (IntrospectionException|IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
		        }

		    }
		 	if(isExist) {
				if(!recommendProduct.containsKey(userId+"")){
					recommendProduct.put(userId+"", new ArrayList<Recommend>());
				}
				
				Recommend recommend = new Recommend();
				recommend.setCourseId(course.getId());
				recommend.setUserId(userId);
				recommend.setScore(Double.valueOf(score).longValue());
				if(userScourseMap.containsKey(userId+"_"+course.getId())){
					recommend.setSelected('1');
				}else {
					recommend.setSelected('0');
				}
				if(recommendProduct.containsKey(userId+"")) {
					recommendProduct.get(userId+"").add(recommend);
				}else {
					continue;
				}
				System.out.println("userId: " +userId + " courseId:" + course.getId()+" score:"+Double.valueOf(score).longValue()+" selected:"+recommend.getSelected());
			}
		}
	}
	
	/**
	 * calculate user's course score.
	 * @param userId
	 * @return
	 * @throws InterruptedException
	 */
	public Future<String> calculateCourse(Long userId) throws InterruptedException{
		long begin = System.currentTimeMillis();
    	List<Course> cList = courseService.findAll();
    	recommendService.deleteByUserId(userId);
    	if(recommendProduct.containsKey(userId+"")){
			recommendProduct.remove(userId+"");
		}
    	
    	calculateUserCourseRecommendList(cList,userId);
    	
    	if(recommendProduct.containsKey(userId+"")) {
    		recommendService.save(recommendProduct.get(userId+""));
    	}
       
        long end = System.currentTimeMillis();
        System.out.println("========================>>>>>>calculateCourse(Long userId) time cost ="+(end-begin)+" ms.");
        return new AsyncResult<String>("calculateCourse(Long userId)");
	}
}

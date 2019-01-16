package com.vin.controller;

import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vin.entity.UserCourse;
import com.vin.service.UserCourseService;

@RestController
@RequestMapping("/selectedcourses")
public class SelectedCourseController {
	
	static Logger log = LogManager.getLogger( SelectedCourseController.class.getName());
	
	@Autowired
    private AsyncCalculateTask calculatetask;
	
    @Resource
    private UserCourseService userCourseService;
  
    /**
     * get Course info.
     * @param id: Course's id.
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserCourse get(@PathVariable("id") Long id) {
         List<UserCourse> cl = userCourseService.findByUserId(Long.valueOf(id));
         if(cl.size()>=0) {
        	 return cl.get(0);
         }
        return null;
    }
    
    @RequestMapping(value = "/calculateScore", method = RequestMethod.GET)
    public void exeTask() throws InterruptedException{
        
        long begin = System.currentTimeMillis();
        
        Future<String> calMetrics = calculatetask.initUserCourseMetrics();
        while(true){
	      if (calMetrics.isDone()) {
	    	  calculatetask.calculateCourse();
	          break;
	      }
        }
        
        long end = System.currentTimeMillis();    
        long total = end-begin;
        System.out.println("calculateScore time cost ="+total+" ms.");
    }
    
    @RequestMapping(value = "/calculateUserScore/{id}", method = RequestMethod.GET)
    public void exeTask(@PathVariable("id") Long id) throws InterruptedException{
        
        long begin = System.currentTimeMillis();
        
	    calculatetask.calculateCourse(id);
        
        long end = System.currentTimeMillis();    
        long total = end-begin;
        System.out.println("calculateUserScore time cost ="+total+" ms.");
    } 
}
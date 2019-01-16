package com.vin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vin.entity.Recommend;
import com.vin.service.CourseService;
import com.vin.service.RecommendService;

@RestController
@RequestMapping("/recommendcourses")
public class RecommendCourseController {
	
	static Logger log = LogManager.getLogger( RecommendCourseController.class.getName());
	
    @Resource
    private CourseService courseService;
    
    @Resource
    private RecommendService recommendService;
    
    /**
     * get user's Course info.
     * @param id: user id.
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Recommend> get(@PathVariable("id") Long id) {
        List<Recommend> recommendList = recommendService.findByUserId(id);
        return recommendList;
    }
    
    @RequestMapping(value = "/{id}/{selected}", method = RequestMethod.GET)
    public List<Recommend> get(@PathVariable("id") Long id,@PathVariable("selected") char selected) {
        List<Recommend> recommendList = recommendService.findByUserIdAndSelected(id,selected);
        return recommendList;
    }
}
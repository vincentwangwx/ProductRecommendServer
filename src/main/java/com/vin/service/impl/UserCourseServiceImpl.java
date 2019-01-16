package com.vin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.dao.UserCourseDao;
import com.vin.entity.UserCourse;
import com.vin.service.UserCourseService;

@Service
public class UserCourseServiceImpl implements UserCourseService {
	    @Autowired
	    private UserCourseDao userCourseDao;

	    public List<UserCourse> findByUserId(long userId){
	    	
	    	return userCourseDao.findByUserId(Long.valueOf(userId));
	    }
	    
	    public List<UserCourse> fillAll(){
	    	return userCourseDao.findAll();
	    }

}
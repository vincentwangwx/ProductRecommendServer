package com.vin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.dao.CourseDao;
import com.vin.entity.Course;
import com.vin.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	 @Autowired
	private CourseDao  courseDao ;
	 
	public Optional<Course> findCourseById(Long courseId) {
		return courseDao.findById(courseId);
	}
	public List<Course> findAll() {
		return courseDao.findAll();
	}

}
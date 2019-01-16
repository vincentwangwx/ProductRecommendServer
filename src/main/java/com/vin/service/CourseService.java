package com.vin.service;

import java.util.List;
import java.util.Optional;

import com.vin.entity.Course;

public interface CourseService {
	public Optional<Course> findCourseById(Long courseId);
	public List<Course> findAll() ;
}
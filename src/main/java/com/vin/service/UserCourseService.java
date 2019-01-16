package com.vin.service;

import java.util.List;

import com.vin.entity.UserCourse;

public interface UserCourseService {
	 public List<UserCourse> findByUserId(long userId);
	 public List<UserCourse> fillAll();
}
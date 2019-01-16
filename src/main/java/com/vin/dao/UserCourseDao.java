package com.vin.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.UserCourse;
import com.vin.entity.UserCourseKey;

public interface UserCourseDao extends JpaRepository<UserCourse, UserCourseKey> {
  
   List<UserCourse> findByCourseId(Long courseId);
   List<UserCourse> findByUserId(Long userId);
   List<UserCourse> findByCourseIdAndUserId(Long userId,Long projectId);
   
}
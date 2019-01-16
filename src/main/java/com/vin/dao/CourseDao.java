package com.vin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Course;

public interface CourseDao extends JpaRepository<Course, Long> {

}
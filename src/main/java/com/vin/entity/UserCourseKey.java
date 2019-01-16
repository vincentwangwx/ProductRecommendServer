package com.vin.entity;

import java.io.Serializable;

public class UserCourseKey implements Serializable{
	private static final long serialVersionUID = 1L;
	public UserCourseKey() {
		// TODO Auto-generated constructor stub
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	private long courseId;
	private long userId;
}

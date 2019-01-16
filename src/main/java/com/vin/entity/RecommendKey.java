package com.vin.entity;

import java.io.Serializable;

public class RecommendKey implements Serializable{
	private static final long serialVersionUID = 1L;
	public RecommendKey() {
		// TODO Auto-generated constructor stub
	}
	public RecommendKey(long userId) {
		this.userId = userId;
	}
	private long courseId;
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
	private long userId;
}

package com.vin.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;;


@Entity 
@Table(name="prs_user_course")
@DynamicUpdate(true)
@IdClass(UserCourseKey.class)
public class UserCourse {

	@Id
	@Column(name = "courseId")
	private long courseId;

	@Id
	@Column(name = "userId")
	private long userId;

	@Column(name = "select_time")
	private String selectTime;

	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
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
}
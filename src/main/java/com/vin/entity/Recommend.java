package com.vin.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;;


@Entity 
@Table(name="prs_recommend")
@DynamicUpdate(true)
@IdClass(RecommendKey.class)
@EntityListeners(AuditingEntityListener.class)
public class Recommend {
	
    @Id
    @Column(name = "courseId")
    private long courseId;
    
    @Id
    @Column(name = "userId")
    private long userId;
    
    private long sort;
    
    private long score;

    private char selected;
    
    @CreatedDate
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public char getSelected() {
		return selected;
	}

	public void setSelected(char selected) {
		this.selected = selected;
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

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

}
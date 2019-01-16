package com.vin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;;

@Entity
@Table(name = "prs_course")
@DynamicUpdate(true)
public class Course {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "author_id")
	private Author author;

	@Column(name = "length")
	private int length;

	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private String category = "";

	@Column(name = "summary")
	private String summary = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
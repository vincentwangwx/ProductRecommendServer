package com.vin.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "prs_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "create_datetime")
    /*
       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", locale = "zh", timezone = "UTC")
       @Type(type="datetime")
    */
    private String create_datetime;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name="pre_user_course",joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="course_id"))
    private List<Course> selectedCourseList;
    
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

	public List<Course> getSelectedCourseList() {
		return selectedCourseList;
	}

	public void setSelectedCourseList(List<Course> selectedCourseList) {
		this.selectedCourseList = selectedCourseList;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

   

}
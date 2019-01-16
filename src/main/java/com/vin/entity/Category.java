package com.vin.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prs_category")
public class Category implements Serializable {

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

   
}
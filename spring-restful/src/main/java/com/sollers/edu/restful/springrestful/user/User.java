package com.sollers.edu.restful.springrestful.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonFilter("DynamicBeanFilter")  
public class User {
	
	
	private Integer id;
	
	//@Size(min=2)
	private String name;
	
//	@Past
	
	private Date dOB;
	
	private String job;
	
	

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public User()
	{
		
	}
	
	public User(Integer id, String name, Date dOB, String job) {
		super();
		this.id = id;
		this.name = name;
		this.dOB = dOB;
		this.job = job;	
	}


	public User(Integer id, String name, Date dOB) {
		super();
		this.id = id;
		this.name = name;
		this.dOB = dOB;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getdOB() {
		return dOB;
	}

	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, dOB);
	}
	
	

}

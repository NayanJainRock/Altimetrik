package com.nayan.employee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.ToString;


@Entity
@Table(name="employees_data")
@IdClass(Employee.class)
@ToString
public class Employee  implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@Id	
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(name="id")
//    private Long id;  
	
	@Id
	@Column(name="department")	
	private String department;
	
	@Id
	@Column(name="project")	
	private String project;
	
	@Id
	@Column(name="name")
    private String name;
	
	
//	public long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	} 
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	} 
	
}


package com.simplej.rest.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Project {
	@NotNull
	private Integer id;

	@NotBlank
	@Length(min = 2, max = 255)
	private String name;

	@Length(min = 2, max = 255)
	private String color;

	public Project() {
	}

	public Project(Integer id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Project [ id=" + id + ", name=" + name + ", color=" + color + " ]";
	}
}
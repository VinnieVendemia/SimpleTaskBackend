package com.simplej.rest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.QueryHints;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@DynamicUpdate
@Table(name="project")
@NamedQueries({ @NamedQuery(name="findProjects", query="SELECT OBJECT(o) FROM Project o ORDER BY o.id" , hints={@QueryHint(name=QueryHints.CACHEABLE, value="true") } ) } )
public class Project {
	
	@Id @NotNull
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
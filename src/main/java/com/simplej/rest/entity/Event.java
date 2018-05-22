package com.simplej.rest.entity;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Event {
 	@NotNull
    private Integer id;
 	
 	@NotBlank @Length(min=1, max=255)
    private String title;
 	
 	@NotNull
    private Date date;
 	
 	@NotNull @Min(0) @Max(100)
    private Integer rank;
 	
    @NotBlank @Length(min=1, max=3000)
    private String description;
    
    @Length(min=2, max=255)
    private String color;
     
    public Event(){
    }
 
    public Event(Integer id, String title, Date date, Integer rank, String description, String color) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.rank = rank;
        this.description = description;
        this.color = color;
    }
    
	public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", date=" + date + ", rank=" + rank + ", description="
				+ description + ", color=" + color + "]";
	}
}

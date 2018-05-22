package com.simplej.rest.controller;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.simplej.rest.dao.ProjectDB;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectRESTController {
	private final Validator validator;
	
	public ProjectRESTController(Validator validator) {
        this.validator = validator;
    }
	
	@GET
    public Response getProjects() {
        return Response.ok(ProjectDB.getProjects()).build();
    }
	
	// GET BY ID 
	
	// POST 
	
	// PUT 
	
	// DELETE 
}
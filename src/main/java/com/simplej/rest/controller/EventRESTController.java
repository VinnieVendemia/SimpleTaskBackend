package com.simplej.rest.controller;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.simplej.rest.dao.EventDB;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventRESTController {
	private final Validator validator;
	
	public EventRESTController(Validator validator) {
        this.validator = validator;
    }
	
	@GET
    public Response getEvents() {
        return Response.ok(EventDB.getEvents()).build();
    }
	
	// GET BY ID 
	
	// POST 
	
	// PUT 
	
	// DELETE 
}
package com.simplej.rest.controller;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.simplej.rest.dao.ClientDB;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
public class ClientRESTController {

	private final Validator validator;
	
	public ClientRESTController(Validator validator) {
        this.validator = validator;
    }
	
	@GET
    public Response getClients() {
        return Response.ok(ClientDB.getClients()).build();
    }	
}

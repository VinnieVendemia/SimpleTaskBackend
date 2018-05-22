package com.simplej.rest.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.simplej.rest.dao.EventDB;
import com.simplej.rest.entity.Event;

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
	
    @GET
    @Path("/{id}")
    public Response getEventById(@PathParam("id") Integer id) {
        Event event = EventDB.getEvent(id);
        if (event != null)
            return Response.ok(event).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }
 
	@POST
	public Response createEvent(Event event) throws URISyntaxException {
		// validation
		Set<ConstraintViolation<Event>> violations = validator.validate(event);
		Event e = EventDB.getEvent(event.getId());
		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Event> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
		}
		if (e != null) {
			EventDB.updateEvent(event.getId(), event);
			return Response.created(new URI("/events/" + event.getId())).build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}
 
    @PUT
    @Path("/{id}")
    public Response updateEventById(@PathParam("id") Integer id, Event event) {
        // validation
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        Event e = EventDB.getEvent(event.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Event> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            event.setId(id);
            EventDB.updateEvent(id, event);
            return Response.ok(event).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response removeEventById(@PathParam("id") Integer id) {
    	Event event = EventDB.getEvent(id);
        if (event != null) {
        	EventDB.removeEvent(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
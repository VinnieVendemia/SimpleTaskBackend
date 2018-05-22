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
import com.codahale.metrics.annotation.Timed;
import com.simplej.rest.dao.ProjectDB;
import com.simplej.rest.entity.Project;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectRESTController {
	private final Validator validator;
	private ProjectDB dao;

	public ProjectRESTController(Validator validator, ProjectDB dao) {
		this.validator = validator;
		this.dao = dao; 
	}

	@GET
	@Timed
	@UnitOfWork
	public Response getProjects() {
		return Response.ok(dao.getProjects()).build();
	}

	@GET
	@Timed
	@UnitOfWork
	@Path("/{id}")
	public Response getProjectById(@PathParam("id") Integer id) {
		Project proj = dao.getProject(id);
		if (proj != null)
			return Response.ok(proj).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	@Timed
	@UnitOfWork
	public Response createProject(Project proj) throws URISyntaxException {
		Set<ConstraintViolation<Project>> violations = validator.validate(proj);
		Project e = dao.getProject(proj.getId());
		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Project> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
		}
		if (e != null) {
			return Response.status(Status.CONFLICT).build();
		} else
			dao.createProject(proj);
			return Response.created(new URI("/projects/" + proj.getId())).build();
	}

    @PUT
    @Timed
	@UnitOfWork
    @Path("/{id}")
    public Response updateProjectById(@PathParam("id") Integer id, Project project) {
        // TODO
    	return Response.status(Status.METHOD_NOT_ALLOWED).build();
    }
 
    @DELETE
    @Timed
	@UnitOfWork
    @Path("/{id}")
    public Response removeProjectById(@PathParam("id") Integer id) {
    	Project project = dao.getProject(id);
        if (project != null) {
        	dao.removeProject(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
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
import com.simplej.rest.dao.ProjectDB;
import com.simplej.rest.entity.Project;

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

	@GET
	@Path("/{id}")
	public Response getProjectById(@PathParam("id") Integer id) {
		Project proj = ProjectDB.getProject(id);
		if (proj != null)
			return Response.ok(proj).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	// TODO: Need to make this actually create a new project
	@POST
	public Response createProject(Project proj) throws URISyntaxException {
		// validation
		Set<ConstraintViolation<Project>> violations = validator.validate(proj);
		Project e = ProjectDB.getProject(proj.getId());
		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Project> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
		}
		if (e != null) {
			ProjectDB.updateProject(proj.getId(), proj);
			return Response.created(new URI("/projects/" + proj.getId())).build();
		} else
			return Response.status(Status.NOT_FOUND).build();
	}

    @PUT
    @Path("/{id}")
    public Response updateProjectById(@PathParam("id") Integer id, Project project) {
        // validation
        Set<ConstraintViolation<Project>> violations = validator.validate(project);
        Project e = ProjectDB.getProject(project.getId());
        if (violations.size() > 0) {
            ArrayList<String> validationMessages = new ArrayList<String>();
            for (ConstraintViolation<Project> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
        }
        if (e != null) {
            project.setId(id);
            ProjectDB.updateProject(id, project);
            return Response.ok(project).build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
 
    @DELETE
    @Path("/{id}")
    public Response removeProjectById(@PathParam("id") Integer id) {
        Project project = ProjectDB.getProject(id);
        if (project != null) {
        	ProjectDB.removeProject(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
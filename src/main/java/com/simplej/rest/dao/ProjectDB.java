package com.simplej.rest.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import com.simplej.rest.entity.Project;

import io.dropwizard.hibernate.AbstractDAO;

public class ProjectDB extends AbstractDAO<Project> {
	
	public ProjectDB(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<Project> getProjects() {
		return list(namedQuery("findProjects"));
	}

	public Project getProject(Integer id) {
		return get(id);
	}
	
	public long createProject(Project project) {
		return persist(project).getId();
	}

	public void updateProject(Integer id, Project project) {
		// TODO
	}

	public boolean removeProject(Integer id) {
		final Project record = get(id);
		if (null == record)
			return false;

		currentSession().delete(record);
		return true;
	}
}

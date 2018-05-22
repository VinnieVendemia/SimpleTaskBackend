package com.simplej.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.simplej.rest.entity.Project;

public class ProjectDB {
	public static HashMap<Integer, Project> projects = new HashMap<>();
	static {
		projects.put(1, new Project(1, "Project 1", "red"));
		projects.put(2, new Project(2, "Project 2", "blue"));
		projects.put(3, new Project(3, "Project 3", "green"));
	}

	public static List<Project> getProjects() {
		return new ArrayList<Project>(projects.values());
	}

	public static Project getProject(Integer id) {
		return projects.get(id);
	}

	public static void updateProject(Integer id, Project project) {
		projects.put(id, project);
	}

	public static void removeProject(Integer id) {
		projects.remove(id);
	}
}

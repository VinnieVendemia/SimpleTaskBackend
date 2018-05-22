package com.simplej.rest.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.SessionFactory;
import com.simplej.rest.entity.Event;
import io.dropwizard.hibernate.AbstractDAO;

public class EventDB extends AbstractDAO<Event> {
	
	public EventDB(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public static HashMap<Integer, Event> events = new HashMap<>();
	static {
		events.put(1, new Event(1, "Event 1", todaysDate(), 50, "TEST DESCRIPTION 1", "red"));
		events.put(2, new Event(2, "Event 2", todaysDate(), 50, "TEST DESCRIPTION 2", "blue"));
		events.put(3, new Event(3, "Event 3", todaysDate(), 50, "TEST DESCRIPTION 3", "green"));
	}

	public List<Event> getEvents() {
        return list(namedQuery("findEvents"));
	}

	public Event getEvent(Integer id) {
		return get(id);
	}

	public static void updateEvent(Integer id, Event event) {
		events.put(id, event);
	}

	public static void removeEvent(Integer id) {
		events.remove(id);
	}

	// private methods

	private static Date todaysDate() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		return today.getTime();
	}
}
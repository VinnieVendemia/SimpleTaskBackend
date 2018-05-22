package com.simplej.rest.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import com.simplej.rest.entity.Event;
import io.dropwizard.hibernate.AbstractDAO;

public class EventDB extends AbstractDAO<Event> {
	
	public EventDB(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Event> getEvents() {
        return list(namedQuery("findEvents"));
	}

	public Event getEvent(Integer id) {
		return get(id);
	}
	
	public long createEvent(Event event) {
		return persist(event).getId();
	}

	public void updateEvent(Integer id, Event event) {
		// TODO		
	}

	public boolean removeEvent(Integer id) {
		final Event record = get(id);
		if (null == record)
			return false;

		currentSession().delete(record);
		return true;
	}

	// private methods

	private static Date todaysDate() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		return today.getTime();
	}
}
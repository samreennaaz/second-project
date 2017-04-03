package com.collaboration.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaboration.model.Event;

@Transactional
@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addEvent(Event event) {
		
		sessionFactory.getCurrentSession().save(event);
	}

	public List<Event> listEvent() {
		
		@SuppressWarnings("unchecked")
		List<Event> listEvent = (List<Event>)sessionFactory.getCurrentSession().createCriteria(Event.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listEvent;
	}

	public void delete(int eventId) {
		
		Event eventToDelete = new Event();
		eventToDelete.setEventId(eventId);
		sessionFactory.getCurrentSession().delete(eventToDelete);
		
	}

	public Event get(int eventId) {
		
		String hql = "from Event where eventID=" + "'" + eventId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Event> listEvent = (List<Event>) query.list();

		if (listEvent != null && !listEvent.isEmpty()) {
			return listEvent.get(0);
		}
		return null;
	}

	public void updateEvent(Event event) {
		
		Event e =get(event.getEventId());
		e.setName(event.getName());
		e.setVenue(event.getVenue());
		e.setDescription(event.getDescription());
		e.setDateTime(event.getDateTime());
		sessionFactory.getCurrentSession().update(e);
	}

	public Event view(int eventId) {
		
		String hql = "from Event where eventID=" + "'" + eventId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Event> listEvent = (List<Event>) query.list();

		if (listEvent != null && !listEvent.isEmpty()) {
			return listEvent.get(0);
		}
		return null;	
	}

}

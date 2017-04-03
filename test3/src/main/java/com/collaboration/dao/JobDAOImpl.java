/*package com.collaboration.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.collaboration.model.Job;
import com.collaboration.model.JobApplication;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean postJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAllVacantJobs() {
		String hql="from Job where status= 'v'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public boolean applyForJob(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public JobApplication get(int userId, int jobId) {
		String hql="from JobApplication where userId= '"+userId+"' and jobId='"+jobId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication)query.list();
	}

	public JobApplication getMyAppliedJobs(int userId) {
		String hql="from JobApplication where userId= '"+userId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication)query.list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Job> getAllJobs() {
		String hql="from Job";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();	
	}
}
*/
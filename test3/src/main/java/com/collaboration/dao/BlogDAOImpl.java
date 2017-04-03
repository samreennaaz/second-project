package com.collaboration.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.collaboration.model.Blog;

@Transactional
@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addBlog(Blog blog) {
		
		sessionFactory.getCurrentSession().save(blog);
	}

	public List<Blog> listBlog() {
		
		@SuppressWarnings("unchecked")
		List<Blog> listBlog = (List<Blog>)sessionFactory.getCurrentSession().createCriteria(Blog.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listBlog;
	}
 
	public void delete(int blogId) {
		
		Blog blogToDelete = new Blog();
		blogToDelete.setBlogId(blogId);
		sessionFactory.getCurrentSession().delete(blogToDelete);	
	}

	public Blog get(int blogId) {
		
		String hql = "from Blog where blogID=" + "'" + blogId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Blog> listBlog = (List<Blog>) query.list();

		if (listBlog != null && !listBlog.isEmpty()) {
			return listBlog.get(0);
		}
		return null;
	}

	public void updateBlog(Blog blog) {
		
		Blog b =get(blog.getBlogId());
		b.setTitle(blog.getTitle());
		b.setDescription(blog.getDescription());
		sessionFactory.getCurrentSession().update(b);
		
	}

	public Blog view(int blogId) {
		
		String hql = "from Blog where blogID=" + "'" + blogId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Blog> listBlog = (List<Blog>) query.list();

		if (listBlog != null && !listBlog.isEmpty()) {
			return listBlog.get(0);
		}
		return null;
	}

}

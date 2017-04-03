package com.collaboration.dao;

import java.util.List;

import com.collaboration.model.Blog;

public interface BlogDAO {

	public void addBlog(Blog blog);
	public List<Blog> listBlog();
	public void delete(int blogId);
	public Blog get(int blogId);
	public void updateBlog(Blog blog);
	public Blog view(int blogId);
}

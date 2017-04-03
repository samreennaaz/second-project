package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.BlogDAO;
import com.collaboration.model.Blog;


@RestController
public class BlogController {

	@Autowired(required = true)
	private BlogDAO blogDAO;
	
	@RequestMapping(value="/blog", method=RequestMethod.GET)
	public ResponseEntity<List<Blog>> listBlogs(){
		
		List<Blog> blog = blogDAO.listBlog();
		if(blog.isEmpty()){		
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);		
		}	
 		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/blog", method=RequestMethod.POST)
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		
		if(blogDAO.get(blog.getBlogId())== null)
		{
			blogDAO.addBlog(blog);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/blog/{blogId}", method=RequestMethod.DELETE)
	public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.get(blogId);
		if(blog==null)
		{
			blog=new Blog();
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		blogDAO.delete(blogId);
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/blog/{blogId}", method=RequestMethod.GET)
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId") int blogId)
	{
		Blog blog=blogDAO.get(blogId);
		if(blog==null)
		{
			blog=new Blog();
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@RequestMapping(value="/blog/{blogId}", method=RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog){
		
		if(blogDAO.get(blog.getBlogId())== null)
		{
			blog=new Blog();
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);	
		}
		blogDAO.updateBlog(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}

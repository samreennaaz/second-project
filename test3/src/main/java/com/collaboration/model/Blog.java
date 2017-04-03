package com.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="C_Blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogId;
	private String title;
	private String description;
	private int userId;
	@ManyToOne
	@JoinColumn(name="userId",insertable=false,updatable=false,nullable=false)
	private User user;
	
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String toString()
	{
			return "{blogId:'"+ blogId +"'," + "title:'"+ title +"'," + "description:'"+ description +"'," + "userId:'"+ userId +"'}";
	}
}

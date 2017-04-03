/*package com.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.collaboration.dao.UserDAO;
import com.collaboration.model.User;

@RestController
public class AnotherController {

	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user){		
			userDao.addUser(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);		
	}
	
	@RequestMapping(value="/getusers", method=RequestMethod.GET)
	public ResponseEntity <List<User>> displayUsers(){
		List <User> users = userDao. listUser();
		System.out.println(users);
		if(users.isEmpty())
			return new ResponseEntity <List<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<User>>(users,HttpStatus.ACCEPTED);		
	}
	
	
}*/

package com.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.FriendDAO;
import com.collaboration.dao.UserDAO;
import com.collaboration.model.User;


@RestController
public class UserController {
	
	@Autowired(required = true)
	private UserDAO userDAO;
	
	@Autowired(required = true)
	private FriendDAO friendDAO;
	
	
	//http://localhost:8085/test3/user/
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseEntity<List<User>> listUsers(){
		
		List<User> user = userDAO.listUser();
		if(user.isEmpty()){		
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);		
		}	
		return new ResponseEntity<List<User>>(user,HttpStatus.OK);	
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		if(userDAO.get(user.getUserId())== null)
		{
			user.setIsOnline('N');
			userDAO.addUser(user);
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	//http://localhost:8085/test3/user/1
	@RequestMapping(value="/user/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("userId") int userId)
	{
		User user=userDAO.get(userId);
		if(user==null)
		{
			user=new User();
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
		userDAO.delete(userId);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId)
	{
		User user=userDAO.get(userId);
		if(user==null)
		{
			user=new User();
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/myProfile", method=RequestMethod.GET)
	public ResponseEntity<User> myProfile(HttpSession session)
	{
	int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		//int loggedInUserID=9;
		User user=userDAO.get(loggedInUserID);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{userId}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		
		if(userDAO.get(user.getUserId())== null)
		{
			user=new User();
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);	
		}
		userDAO.updateUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/authenticate/", method=RequestMethod.POST)
	public ResponseEntity<User> authenticate(@RequestBody User us,HttpSession session)
	{
		System.out.println("\nAutjhenticate in controller");
//	int loggedInUserId=10;
User user=userDAO.authenticate(us.getUsername(), us.getPassword());	
		if(user==null)
		{
			user=new User();
			user.setErrorCode("403");
			user.setErrorMessage("Invalid Credentials. Please enter valid credentials");
		}
		else
		{
			user.setErrorCode("200");
			session.setAttribute("loggedInUser", user);
			System.out.println("\nuser id is user controller : "+user.getUserId());
		session.setAttribute("loggedInUserId", user.getUserId());
		friendDAO.setOnline(user.getUserId());
			userDAO.setOnline(user.getUserId());
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout", method=RequestMethod.GET)
	public String logout(HttpSession session)
	{
//		int loggedInUserID=12;
//	int 	loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		friendDAO.setOffline(loggedInUserID);
		userDAO.setOffline(loggedInUserID);
		session.invalidate();
		return ("you successfully logged out");
	}
}


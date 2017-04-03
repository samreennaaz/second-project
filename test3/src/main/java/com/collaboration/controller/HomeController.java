package com.collaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.collaboration.dao.UserDAO;
import com.collaboration.model.User;


@Controller
public class HomeController {
		
			@Autowired
			UserDAO userDAO;

			@RequestMapping("/")
		 String Test()
			{
				System.out.println("i m inside the controller");
			/*	User user=new User();
				user.setUsername("pooja");
		    	user.setPassword("pooja");
		    	user.setName("pooja");
		    	user.setAddress("Hyderabad");
		    	user.setEmail("pooja@gmail.com");
		    	user.setMobile("1234567890");
		    	user.setRole("user");
		    	user.setIsOnline('n');
				userDAO.addUser(user);*/
				
				return "index";
				
			}

		}

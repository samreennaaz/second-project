package com.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.dao.FriendDAO;
import com.collaboration.model.Friend;


@RestController
public class FriendController {
	
	@Autowired(required = true)
	private FriendDAO friendDAO;
	
	@Autowired
	private Friend friend;
	
	@RequestMapping(value="/myFriends", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session)
	{
		
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		List<Friend> myFriends = friendDAO.getMyFriends(loggedInUserID);
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addFriend/{friendId}", method=RequestMethod.POST)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendId") int friendId, HttpSession session)
	{
		System.out.println("\nuser id is friend controller : "+session.getAttribute("loggedInUserId"));
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		Friend fr = new Friend();
		fr.setUserId(loggedInUserID);
		fr.setFriendId(friendId);
		fr.setIsOnline('N');
		fr.setStatus("N");
		friendDAO.save(fr);
		return new ResponseEntity<Friend>(fr, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/unFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> unFriend(@PathVariable("friendId") int friendId, HttpSession session)
	{
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
//		friend.setUserId(loggedInUserID);
		friend.setFriendId(loggedInUserID);
		friend.setIsOnline('Y');
		friend.setStatus("U");
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/rejectFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendId") int friendId, HttpSession session)
	{
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		/*friend.setUserId(loggedInUserID);*/
		friend.setFriendId(loggedInUserID);
		friend.setIsOnline('Y');
		friend.setStatus("R");
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);		
		
	}
	
	@RequestMapping(value="/getMyFriendRequests/", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriendRequests(HttpSession session)
	{
		System.out.println("\nFriend Controller - getMyFriendRequests");
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		List<Friend> myFriendRequests = friendDAO.getNewFriendRequests(loggedInUserID);
		return new ResponseEntity<List<Friend>>(myFriendRequests, HttpStatus.OK);
	}
	
	@RequestMapping(value="/acceptFriend/{friendId}", method=RequestMethod.GET)
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendId") int friendId, HttpSession session)
	{
		System.out.println("\nFriend obj id in controller : " +friend.getId() );
		System.out.println("\nUser id : " +session.getAttribute("loggedInUserId") );
		System.out.println("\nReq. Friend id : " +friendId );
		System.out.println("\n Friend id : " +friend.getFriendId() );
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
//		friend.setId(22);
		friend.setUserId(loggedInUserID);
		friend.setFriendId(loggedInUserID);
		friend.setIsOnline('Y');
		friend.setStatus("A");
		friendDAO.update(friend);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/myFriend", method=RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriend(HttpSession session)
	{
		int loggedInUserID = (Integer)session.getAttribute("loggedInUserId");
		List<Friend> myFriend = friendDAO.getMyFriend(loggedInUserID);
		return new ResponseEntity<List<Friend>>(myFriend, HttpStatus.OK);
	}
}

package com.collaboration.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.collaboration.model.Message;
import com.collaboration.model.OutputMessage;

@Controller
public class ChatForumController {

		@MessageMapping("/chat_forum")
		@SendTo("/topic/message")
		public OutputMessage sendMessage(Message message)
		{
			return new OutputMessage(message, new Date());
		}
}

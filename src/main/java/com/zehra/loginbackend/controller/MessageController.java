package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.model.WebSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message") // frontend: /app/message
    @SendTo("/topic/players")   // tüm clientlara publish
    public WebSocketMessage handleMessage(WebSocketMessage message) {
        return message;
    }
}

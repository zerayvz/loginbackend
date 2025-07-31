package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.dto.WebSocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    // roomId -> Set of usernames
    private final Map<String, Set<String>> roomPlayers = new ConcurrentHashMap<>();

    public MessageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/game")
    public void handleGameMessage(WebSocketMessage message) {
        String roomId = message.getRoomId();
        String type = message.getType();

        if (roomId == null || roomId.isEmpty()) return;

        // Yeni katılan oyuncuyu listeye ekle
        roomPlayers.putIfAbsent(roomId, new HashSet<>());

        if (type.equals("JOIN")) {
            roomPlayers.get(roomId).add(message.getUsername());

            // Yeni katılana tüm oyuncuların listesi gönderilir
            for (String username : roomPlayers.get(roomId)) {
                WebSocketMessage joinMsg = new WebSocketMessage();
                joinMsg.setType("JOIN");
                joinMsg.setUsername(username);
                joinMsg.setRoomId(roomId);
                messagingTemplate.convertAndSend("/topic/room/" + roomId, joinMsg);
            }

        } else if (type.equals("SELECT") || type.equals("RESET")) {
            messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
        }
    }
}

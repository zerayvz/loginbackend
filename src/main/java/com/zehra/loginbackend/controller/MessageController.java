package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.dto.WebSocketMessage;
import com.zehra.loginbackend.model.Room;
import com.zehra.loginbackend.service.RoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RoomService roomService;

    // roomId -> Set of usernames
    private final Map<String, Set<String>> roomPlayers = new ConcurrentHashMap<>();

    public MessageController(SimpMessagingTemplate messagingTemplate, RoomService roomService) {
        this.messagingTemplate = messagingTemplate;
        this.roomService = roomService;
    }

    @MessageMapping("/game")
    public void handleGameMessage(WebSocketMessage message) {
        String roomId = message.getRoomId();
        String type = message.getType();

        if (roomId == null || roomId.isEmpty()) return;

        // Yeni oda varsa oyuncu listesini başlat
        roomPlayers.putIfAbsent(roomId, new HashSet<>());

        switch (type) {

            case "JOIN":
                roomPlayers.get(roomId).add(message.getUsername());

                for (String username : roomPlayers.get(roomId)) {
                    WebSocketMessage joinMsg = new WebSocketMessage();
                    joinMsg.setType("JOIN");
                    joinMsg.setUsername(username);
                    joinMsg.setRoomId(roomId);
                    messagingTemplate.convertAndSend("/topic/room/" + roomId, joinMsg);
                }
                break;

            case "SELECT":
            case "RESET":
            case "TASK_NEXT":
                messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
                break;

            case "INVITE":
                WebSocketMessage inviteMessage = new WebSocketMessage();
                inviteMessage.setType("INVITE");
                inviteMessage.setRoomId(roomId);
                inviteMessage.setUsername(message.getUsername());
                inviteMessage.setTo(message.getTo());

                messagingTemplate.convertAndSend("/topic/invite/" + message.getTo(), inviteMessage);
                break;

            case "REVEAL":
                messagingTemplate.convertAndSend("/topic/room/" + roomId, message);

                // ✅ Oy geçmişini kaydet (task + point + açıklama)
                String task = message.getTask();
                Map<String, Integer> votes = message.getVotes();             // username -> point
                Map<String, String> explanations = message.getExplanations(); // username -> reason

                if (votes != null) {
                    for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                        String username = entry.getKey();
                        int point = entry.getValue();
                        String explanation = explanations != null ? explanations.getOrDefault(username, "") : "";

                        // 🔥 Oy geçmişine kaydet
                        roomService.recordVote(roomId, task, username, point, explanation);
                    }
                }
                break;

            default:
                System.out.println("Unknown message type: " + type);
                break;
        }
    }
}

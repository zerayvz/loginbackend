package com.zehra.loginbackend.dto;

import java.util.Map;

public class WebSocketMessage {

    private String type;       // JOIN, SELECT, RESET, REVEAL vs.
    private String username;
    private String card;
    private String reason;
    private String roomId;
    private String to;         // Davet edilen kullanıcı

    // ✅ REVEAL mesajı için eklenen alanlar:
    private String task;       // Görev başlığı (örneğin: "Login sayfası")
    private Map<String, Integer> votes; // Kullanıcı -> Kart puanı
    private Map<String, String> explanations; // Kullanıcı -> Açıklama

    // 🔽 Getter & Setter'lar

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Map<String, Integer> getVotes() {
        return votes;
    }

    public void setVotes(Map<String, Integer> votes) {
        this.votes = votes;
    }

    public Map<String, String> getExplanations() {
        return explanations;
    }

    public void setExplanations(Map<String, String> explanations) {
        this.explanations = explanations;
    }
}

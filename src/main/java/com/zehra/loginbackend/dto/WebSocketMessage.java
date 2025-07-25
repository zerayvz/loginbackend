package com.zehra.loginbackend.model;

public class WebSocketMessage {
    private String type; // JOIN veya SELECT
    private String username;
    private String card;

    public WebSocketMessage() {
    }

    public WebSocketMessage(String type, String username, String card) {
        this.type = type;
        this.username = username;
        this.card = card;
    }

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
}

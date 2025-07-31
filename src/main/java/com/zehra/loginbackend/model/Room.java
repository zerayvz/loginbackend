package com.zehra.loginbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String title;
    private String ownerUsername;
    private List<String> participants = new ArrayList<>();

    // 🔹 Getter ve Setter'lar

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}

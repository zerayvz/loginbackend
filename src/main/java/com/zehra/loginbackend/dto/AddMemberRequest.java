package com.zehra.loginbackend.dto;

public class AddMemberRequest {
    private Long teamId;
    private String username;

    // Getter and Setter for teamId
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

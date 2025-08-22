package com.zehra.loginbackend.dto;

import java.util.List;

public class RoomCreateRequest {

    private String title;
    private String ownerUsername;
    private List<String> tasks;
    private List<TeamMember> teamMembers;

    // === Inner static class for team member ===
    public static class TeamMember {
        private String username;
        private String role;

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
    }

    // === Getters & Setters for main class ===
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

    public List<String> getTasks() {
        return tasks;
    }
    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }
}

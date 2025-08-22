package com.zehra.loginbackend.model;

import java.util.*;

public class Room {

    private String id;
    private String title;
    private String ownerUsername;
    private Set<String> participants = new HashSet<>();
    private List<String> tasks = new ArrayList<>();
    private Map<String, String> teamMembers = new HashMap<>();
    private String teamName;
    private Map<String, String> taskExplanations = new HashMap<>();
    private Map<String, Map<String, Integer>> taskVotes = new HashMap<>();
    private String roomCode;

    // ✅ Yeni: Geçmiş oy kayıtları (task, username, point, explanation)
    private List<VoteRecord> voteHistory = new ArrayList<>();

    // ✅ Getter ve Setter'lar

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public Set<String> getParticipants() { return participants; }
    public void setParticipants(Set<String> participants) { this.participants = participants; }

    public List<String> getTasks() { return tasks; }
    public void setTasks(List<String> tasks) { this.tasks = tasks; }

    public Map<String, String> getTeamMembers() { return teamMembers; }
    public void setTeamMembers(Map<String, String> teamMembers) { this.teamMembers = teamMembers; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public Map<String, String> getTaskExplanations() { return taskExplanations; }
    public void setTaskExplanations(Map<String, String> taskExplanations) { this.taskExplanations = taskExplanations; }

    public Map<String, Map<String, Integer>> getTaskVotes() { return taskVotes; }
    public void setTaskVotes(Map<String, Map<String, Integer>> taskVotes) { this.taskVotes = taskVotes; }

    public String getRoomCode() { return roomCode; }
    public void setRoomCode(String roomCode) { this.roomCode = roomCode; }

    public List<VoteRecord> getVoteHistory() { return voteHistory; }
    public void setVoteHistory(List<VoteRecord> voteHistory) { this.voteHistory = voteHistory; }

    // ✅ Inner class: Her oylama kaydı
    public static class VoteRecord {
        private String task;
        private String username;
        private int point;
        private String explanation;

        public String getTask() { return task; }
        public void setTask(String task) { this.task = task; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public int getPoint() { return point; }
        public void setPoint(int point) { this.point = point; }

        public String getExplanation() { return explanation; }
        public void setExplanation(String explanation) { this.explanation = explanation; }
    }
}

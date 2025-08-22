package com.zehra.loginbackend.service;

import com.zehra.loginbackend.dto.RoomCreateRequest;
import com.zehra.loginbackend.model.Room;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    private final Map<String, Room> rooms = new HashMap<>();

    // ✅ Yeni createRoom (görevler + ekip üyeleri destekli)
    public Room createRoom(String title, String ownerUsername, List<String> tasks, List<RoomCreateRequest.TeamMember> teamMembers) {
        String shortId = generateShortRoomId();

        Room room = new Room();
        room.setId(shortId);
        room.setRoomCode(shortId);  // ✅ roomCode ekle
        room.setTitle(title);
        room.setOwnerUsername(ownerUsername);

        Set<String> participants = new HashSet<>();
        participants.add(ownerUsername);
        room.setParticipants(participants);

        if (tasks != null) {
            room.setTasks(new ArrayList<>(tasks));
        }

        if (teamMembers != null) {
            Map<String, String> team = new HashMap<>();
            for (RoomCreateRequest.TeamMember member : teamMembers) {
                team.put(member.getUsername(), member.getRole());
                participants.add(member.getUsername());
            }
            room.setTeamMembers(team);
        }

        room.setTaskVotes(new HashMap<>());
        room.setTaskExplanations(new HashMap<>());

        rooms.put(shortId, room);
        return room;
    }

    // ✅ Yeni: Geçmiş oylamaları kaydet
    public void recordVote(String roomId, String task, String username, int point, String explanation) {
        Room room = rooms.get(roomId);
        if (room == null) return;

        Room.VoteRecord record = new Room.VoteRecord();
        record.setTask(task);
        record.setUsername(username);
        record.setPoint(point);
        record.setExplanation(explanation);

        room.getVoteHistory().add(record);
    }

    // 🔹 Katılım
    public boolean joinRoom(String roomId, String username) {
        Room room = rooms.get(roomId);
        if (room != null && username != null && !username.isEmpty()) {
            if (!room.getParticipants().contains(username)) {
                room.getParticipants().add(username);
            }
            return true;
        }
        return false;
    }

    // 🔹 Oda ID'si ile odayı bul
    public Optional<Room> getRoomById(String id) {
        return Optional.ofNullable(rooms.get(id));
    }

    // 🔹 Tüm odaları getir
    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    // 🔹 5 karakterlik rastgele oda kodu üret
    private String generateShortRoomId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}

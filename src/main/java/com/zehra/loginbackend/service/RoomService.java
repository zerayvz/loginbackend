package com.zehra.loginbackend.service;

import com.zehra.loginbackend.model.Room;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomService {

    private final Map<String, Room> rooms = new HashMap<>();

    // 🔹 Oda oluşturma
    public Room createRoom(String title, String ownerUsername) {
        String shortId = generateShortRoomId();

        Room room = new Room();
        room.setId(shortId);
        room.setTitle(title);
        room.setOwnerUsername(ownerUsername);
        room.getParticipants().add(ownerUsername); // Oluşturan kişi otomatik katılır

        rooms.put(shortId, room);
        return room;
    }

    // 🔹 Belirli oda ID'siyle odaya katılma
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

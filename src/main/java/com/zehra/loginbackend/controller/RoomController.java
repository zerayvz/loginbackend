package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.dto.RoomCreateRequest;
import com.zehra.loginbackend.model.Room;
import com.zehra.loginbackend.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;



import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // ✅ Yeni: Proje, görev ve ekip üyeleriyle oda oluşturma
    @PostMapping("/create")
    public Room createRoom(@RequestBody RoomCreateRequest request) {
        return roomService.createRoom(
                request.getTitle(),
                request.getOwnerUsername(),
                request.getTasks(),
                request.getTeamMembers()
        );
    }

    // ✅ Var olan oda katılım endpoint’i
    @PostMapping("/join")
    public String joinRoom(@RequestBody java.util.Map<String, String> payload) {
        String roomId = payload.get("roomId");
        String username = payload.get("username");
        boolean success = roomService.joinRoom(roomId, username);
        return success ? "joined" : "failed";
    }

    // ✅ Tekil oda getir
    @GetMapping("/{id}")
    public Optional<Room> getRoom(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    // ✅ Tüm odaları getir (genellikle debug için)
    @GetMapping
    public Collection<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
    @GetMapping("/{roomId}/history")
    public List<Room.VoteRecord> getVoteHistory(@PathVariable String roomId) {
        return roomService.getRoomById(roomId)
                .map(Room::getVoteHistory)
                .orElse(Collections.emptyList());
    }

}

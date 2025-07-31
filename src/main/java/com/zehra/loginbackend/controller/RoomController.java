package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.model.Room;
import com.zehra.loginbackend.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public Room createRoom(@RequestBody Map<String, String> payload) {
        String title = payload.get("title");
        String owner = payload.get("ownerUsername");
        return roomService.createRoom(title, owner);
    }

    @PostMapping("/join")
    public String joinRoom(@RequestBody Map<String, String> payload) {
        String roomId = payload.get("roomId");
        String username = payload.get("username");
        boolean success = roomService.joinRoom(roomId, username);
        return success ? "joined" : "failed";
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoom(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    @GetMapping
    public Collection<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}

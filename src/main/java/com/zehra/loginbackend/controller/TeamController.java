package com.zehra.loginbackend.controller;

import com.zehra.loginbackend.dto.AddMemberRequest;
import com.zehra.loginbackend.dto.CreateTeamRequest;
import com.zehra.loginbackend.model.Team;
import com.zehra.loginbackend.model.UserEntity;
import com.zehra.loginbackend.repository.TeamRepository;
import com.zehra.loginbackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamController(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    // ✅ Create new team
    @PostMapping
    public ResponseEntity<?> createTeam(
            @RequestBody CreateTeamRequest request,
            @AuthenticationPrincipal User currentUser
    ) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login first");
        }

        Team team = new Team();
        team.setTeamName(request.getTeamName());   // ✅ Team.java'da var
        team.setDescription(request.getDescription());
        team.setMembers(new ArrayList<>());

        // creator bilgisi user tablosundan bulunup set edilir
        userRepository.findByUsername(currentUser.getUsername())
                .ifPresent(team::setCreatedBy);

        // creator otomatik üye olarak eklenir
        team.getMembers().add(currentUser.getUsername());

        Team savedTeam = teamRepository.save(team);
        return ResponseEntity.ok(savedTeam);
    }

    // ✅ List teams of current user
    @GetMapping("/my")
    public ResponseEntity<?> listMyTeams(@AuthenticationPrincipal User currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login first");
        }

        String username = currentUser.getUsername();
        List<Team> teams = teamRepository.findAll();

        // sadece içinde bulunduğu takımları döndür
        List<Team> myTeams = new ArrayList<>();
        for (Team t : teams) {
            if (t.getMembers() != null && t.getMembers().contains(username)) {
                myTeams.add(t);
            }
        }
        return ResponseEntity.ok(myTeams);
    }

    // ✅ Get members of a team
    @GetMapping("/{id}/members")
    public ResponseEntity<?> getMembers(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login first");
        }

        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Team team = optionalTeam.get();
        List<UserEntity> members = new ArrayList<>();
        if (team.getMembers() != null) {
            for (String username : team.getMembers()) {
                userRepository.findByUsername(username).ifPresent(members::add);
            }
        }
        return ResponseEntity.ok(members);
    }

    // ✅ Add member to a team
    @PostMapping("/add-member")
    public ResponseEntity<?> addMember(@RequestBody AddMemberRequest request, @AuthenticationPrincipal User currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized: Please login first");
        }

        Optional<Team> optionalTeam = teamRepository.findById(request.getTeamId());

        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            if (team.getMembers() == null) {
                team.setMembers(new ArrayList<>());
            }

            if (!team.getMembers().contains(request.getUsername())) {
                team.getMembers().add(request.getUsername());
                teamRepository.save(team);
                return ResponseEntity.ok("Member added successfully");
            } else {
                return ResponseEntity.badRequest().body("User already in team");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

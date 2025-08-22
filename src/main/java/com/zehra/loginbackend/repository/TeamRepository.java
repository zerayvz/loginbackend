package com.zehra.loginbackend.repository;

import com.zehra.loginbackend.model.Team;
import com.zehra.loginbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByCreatedBy(UserEntity createdBy);
}

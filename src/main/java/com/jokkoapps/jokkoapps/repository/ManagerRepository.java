package com.jokkoapps.jokkoapps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jokkoapps.jokkoapps.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	
    Optional<Manager> findByEmail(String email);

    Optional<Manager> findByUsernameOrEmail(String username, String email);

    List<Manager> findByIdIn(List<Long> userIds);

    Optional<Manager> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

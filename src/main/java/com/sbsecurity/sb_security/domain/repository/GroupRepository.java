package com.sbsecurity.sb_security.domain.repository;

import com.sbsecurity.sb_security.domain.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, String> {
    Optional<Group> findByName(String name);
}


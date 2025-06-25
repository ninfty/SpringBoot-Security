package com.sbsecurity.sb_security.api;

import com.sbsecurity.sb_security.domain.entity.Group;
import com.sbsecurity.sb_security.domain.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository repository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Group> save(@RequestBody Group group){
        repository.save(group);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Group>> list(){
        return ResponseEntity.ok(repository.findAll());
    }
}

package com.sbsecurity.sb_security.api;

import com.sbsecurity.sb_security.api.dto.CreateUserDTO;
import com.sbsecurity.sb_security.domain.entity.Group;
import com.sbsecurity.sb_security.domain.entity.User;
import com.sbsecurity.sb_security.domain.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> save(@RequestBody CreateUserDTO body){
        User user = userService.save(body.getUser(), body.getPermissions());

        return ResponseEntity.ok(user);
    }
}

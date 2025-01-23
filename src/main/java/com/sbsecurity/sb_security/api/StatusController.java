package com.sbsecurity.sb_security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Public route ok");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute() {
        return ResponseEntity.ok("Private route ok");
    }
}

package com.sbsecurity.sb_security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr")
public class HrController {

    @GetMapping("/assistant")
    @PreAuthorize("hasAnyRole('HR_ASSISTANT','HR_MANAGER','ADMIN')")
    public ResponseEntity<String> assistant(){
        return ResponseEntity.ok("Assistant route");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('HR_MANAGER','ADMIN')")
    public ResponseEntity<String> manager(){
        return ResponseEntity.ok("Manager route");
    }
}

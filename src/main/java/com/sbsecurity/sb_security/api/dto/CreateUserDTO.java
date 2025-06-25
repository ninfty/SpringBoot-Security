package com.sbsecurity.sb_security.api.dto;

import com.sbsecurity.sb_security.domain.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserDTO {
    private User user;
    private List<String> permissions;
}

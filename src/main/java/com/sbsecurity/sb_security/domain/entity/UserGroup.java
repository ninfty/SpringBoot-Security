package com.sbsecurity.sb_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;

    public UserGroup(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}

package com.sbsecurity.sb_security.domain.service;

import com.sbsecurity.sb_security.domain.entity.Group;
import com.sbsecurity.sb_security.domain.entity.User;
import com.sbsecurity.sb_security.domain.entity.UserGroup;
import com.sbsecurity.sb_security.domain.repository.GroupRepository;
import com.sbsecurity.sb_security.domain.repository.UserGroupRepository;
import com.sbsecurity.sb_security.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserGroupRepository userGroupRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User save(User user, List<String> permissions){
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        userRepository.save(user);

        List<UserGroup> listUserGroup = permissions.stream().map(permission -> {
            Optional<Group> groupName = groupRepository.findByName(permission);

            if(groupName.isPresent()){
                Group group = groupName.get();

                return new UserGroup(user, group);
            }

            return null;
        }).filter(group -> group != null).collect(Collectors.toList());

        userGroupRepository.saveAll(listUserGroup);

        return user;
    }

    public User getUserWithPermissions(String login){
        Optional<User> userOptional = userRepository.findByLogin(login);
        if(userOptional.isEmpty()){
            return null;
        }

        User user = userOptional.get();
        List<String> permissions = userGroupRepository.findPermissionsByUser(user);
        user.setPermissions(permissions);

        return user;
    }
}

package com.example.board.service;

import com.example.board.model.LoginType;
import com.example.board.model.User;
import com.example.board.model.UserRole;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional
    public void signUp(User user) {
        String rawPassword = user.getPassword();
        String encodePassword = encode.encode(rawPassword);
        user.setPassword(encodePassword);
        user.setLoginType(LoginType.GENERAL);
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(User::new);
        return user;
    }

    @Transactional
    public void updateUserInfo(User user) {
        long id = user.getId();
        User currUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("Failed to load User Info : cannot find User id")));

        //Validate
        if (currUser.getLoginType() == LoginType.GENERAL) {
            String rawPassword = user.getPassword();
            String encodePassword = encode.encode(rawPassword);
            currUser.setPassword(encodePassword);
            currUser.setEmail(user.getEmail());
        }
    }
}

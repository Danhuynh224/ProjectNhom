package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import io.swagger.v3.oas.models.info.Contact;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public User registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return null;
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setActive(false); // Chưa kích hoạt
        userRepository.save(user);
        return user;
    }

    public User login(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null || !BCrypt.checkpw(user.getPassword(), existingUser.getPassword()) || !existingUser.isActive()){
            return null;
        }
        return existingUser;
    }
}

package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean addNewUser(User user) {
        UUID uuid = UUID.randomUUID();

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        } else {
            String userID = uuid.toString();
            User newUser = new User(user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getDob(), user.getProfilePic());
            userRepository.save(newUser);
            return true;

        }
    }

    public void editUser(Integer userID, User updatedUser) {
        User user = userRepository.findById(userID).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setDob(updatedUser.getDob());
        user.setProfilePic(updatedUser.getProfilePic());
        userRepository.save(user);
    }

    public void deleteUser(Integer userID) {
        userRepository.deleteById(userID);
    }

    public boolean loginValidator(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            String[] values = credentials.split(":", 2);

            if (userRepository.findByUsername(values[0]).getPassword().equals(values[1])) {
                return true;
            }
        }
        return false;

    }
}

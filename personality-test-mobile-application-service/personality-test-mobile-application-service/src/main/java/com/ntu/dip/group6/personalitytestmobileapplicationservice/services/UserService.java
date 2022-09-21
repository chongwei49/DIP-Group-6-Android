package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.UserRepository;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.security.PasswordHasher;
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

    public String addNewUser(User user) {
        UUID uuid = UUID.randomUUID();

        if (userRepository.findByUsername(user.getUsername()) != null && userRepository.findByEmail(user.getEmail()) != null) {
            return "Email and username duplicate";
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email duplicates";
        } else if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username duplicates";
        }
        else {
            String userID = uuid.toString();
            String salt = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());
            String hashedPassword = PasswordHasher.getSecurePasssword(user.getPassword(), salt.getBytes());
            User newUser = new User(user.getName(), user.getEmail(), user.getUsername(), hashedPassword, user.getDob(), user.getProfilePic());
            userRepository.save(newUser);
            return "Success";

        }
    }

    public void editUser(Integer userID, User updatedUser) {
        User user = userRepository.findById(userID).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        String salt = Base64.getEncoder().encodeToString((updatedUser.getEmail() + ":" + updatedUser.getPassword()).getBytes());
        String hashedPassword = PasswordHasher.getSecurePasssword(updatedUser.getPassword(), salt.getBytes());
        user.setPassword(hashedPassword);
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

            String hashedPassword = PasswordHasher.getSecurePasssword(values[1], base64Credentials.getBytes());

            if (userRepository.findByEmail(values[0]).getPassword().equals(hashedPassword)) {
                return true;
            }
        }
        return false;

    }
}

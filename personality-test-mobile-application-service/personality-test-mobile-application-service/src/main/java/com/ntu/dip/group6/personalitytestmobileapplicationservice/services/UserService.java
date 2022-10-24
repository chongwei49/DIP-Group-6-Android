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

    public User getUserById(Integer userId) {
        if (userRepository.findById(userId).get() != null)
            return userRepository.findById(userId).get();
        else
            return null;
    }

    public String addNewUser(User user) {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if (foundUser == null) {
            String salt = Base64.getEncoder().encodeToString((user.getEmail() + ":" + user.getPassword()).getBytes());
            String hashedPassword = PasswordHasher.getSecurePasssword(user.getPassword(), salt.getBytes());
            User newUser = new User(user.getName(), user.getEmail(), hashedPassword, user.getDob(), user.getGender(), user.getProfilePic());
            userRepository.save(newUser);
            return userRepository.findByEmail(user.getEmail()).getUserId().toString();
        }
        else {
           return "Email duplicates";
        }
    }

    public void editUser(Integer userID, User updatedUser) {
        User user = userRepository.findById(userID).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        String salt = Base64.getEncoder().encodeToString((updatedUser.getEmail() + ":" + updatedUser.getPassword()).getBytes());
        String hashedPassword = PasswordHasher.getSecurePasssword(updatedUser.getPassword(), salt.getBytes());
        user.setPassword(hashedPassword);
        user.setDob(updatedUser.getDob());
        user.setGender(updatedUser.getGender());
        user.setProfilePic(updatedUser.getProfilePic());
        userRepository.save(user);
    }

    public void deleteUser(Integer userID) {
        userRepository.deleteById(userID);
    }

    public Object loginValidator(String authHeader) {
            // Authorization: Basic base64credentials
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            String[] values = credentials.split(":", 2);

            String hashedPassword = PasswordHasher.getSecurePasssword(values[1], base64Credentials.getBytes());

            if (userRepository.findByEmail(values[0]) != null) {
                if (userRepository.findByEmail(values[0]).getPassword().equals(hashedPassword)) {
                    return userRepository.findByEmail(values[0]);
                }
            }

            return "User not found";

    }
}

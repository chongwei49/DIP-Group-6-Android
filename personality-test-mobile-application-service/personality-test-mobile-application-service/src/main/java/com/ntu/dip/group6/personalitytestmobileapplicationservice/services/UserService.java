package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        UUID uuid = UUID.randomUUID();
        String userID = uuid.toString();
        User newUser = new User(user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getDob());
        userRepository.save(newUser);
    }

    public void editUser(Integer userID, User updatedUser) {
        User user = userRepository.findById(userID).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setDob(updatedUser.getDob());
        userRepository.save(user);
    }

    public void deleteUser(Integer userID) {
        userRepository.deleteById(userID);
    }
}

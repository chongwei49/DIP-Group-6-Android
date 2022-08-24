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
        User newUser = new User(userID, user.getName(), user.getEmail(), user.getPassword(), user.getPhone_number(), user.getDate_of_birth());
        userRepository.save(newUser);
    }

    public void editUser(String userID, User updatedUser) {
        User user = userRepository.findById(userID).get();
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setDate_of_birth(updatedUser.getDate_of_birth());
        user.setPhone_number(updatedUser.getPhone_number());
        userRepository.save(user);
    }

    public void deleteUser(String userID) {
        userRepository.deleteById(userID);
    }
}

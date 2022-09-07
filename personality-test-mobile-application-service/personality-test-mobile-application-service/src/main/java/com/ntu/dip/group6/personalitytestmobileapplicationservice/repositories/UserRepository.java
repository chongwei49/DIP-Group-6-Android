package com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByName(String name);

    public User findByUsername(String username);

}

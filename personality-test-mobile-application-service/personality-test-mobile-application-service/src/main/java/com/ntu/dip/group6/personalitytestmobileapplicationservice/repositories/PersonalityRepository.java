package com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonalityRepository extends CrudRepository<Personality, Integer> {

    public List<Personality> findByUserId(Integer userId);

    public List<Personality> findByPersonalityType(String personalityType);
}

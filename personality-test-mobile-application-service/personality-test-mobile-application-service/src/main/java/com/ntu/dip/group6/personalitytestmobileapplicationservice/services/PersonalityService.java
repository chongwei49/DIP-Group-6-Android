package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.PersonalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityService {

    @Autowired
    PersonalityRepository personalityRepository;

    public Iterable<Personality> getAllPersonalities() {
        return personalityRepository.findAll();
    }

    public Personality getPersonalityById(Integer priId) {
        return personalityRepository.findById(priId).get();
    }

    public List<Personality> getPersonalityByUserId(Integer userId) {
        return personalityRepository.findByUserId(userId);
    }

    public void addNewPersonality(Personality personality) {
        Personality newPersonality = new Personality(personality.getUserId(), personality.getQnCategory(), personality.getPersonalityType(), personality.getDateTime());
        personalityRepository.save(newPersonality);
    }

    public void editPersonality(Integer priId, Personality updatedPersonality) {
        Personality personality = personalityRepository.findById(priId).get();
        personality.setUserId(updatedPersonality.getUserId());
        personality.setQnCategory(updatedPersonality.getQnCategory());
        personality.setPersonalityType(updatedPersonality.getPersonalityType());
        personality.setDateTime(updatedPersonality.getDateTime());
        personalityRepository.save(personality);
    }

    public void deletePersonality(Integer priId) {
        personalityRepository.deleteById(priId);
    }
}

package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.PersonalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalityService {

    @Autowired
    PersonalityRepository personalityRepository;

    @Autowired
    TraitsService traitsService;

    public Iterable<Personality> getAllPersonalities() {
        return personalityRepository.findAll();
    }

    public Personality getPersonalityById(Integer priId) {
        return personalityRepository.findById(priId).get();
    }

    public List<Personality> getPersonalityByUserId(Integer userId) {
        return personalityRepository.findByUserId(userId);
    }

    public Map<String, String> addNewPersonality(Personality personality) {
        Date now = new Date();
        Personality newPersonality = new Personality(personality.getUserId(), personality.getQnCategory(), personality.getPersonalityType(), now);
        personalityRepository.save(newPersonality);
        Traits traits = traitsService.getTraitByPersonalityType(personality.getPersonalityType());
        Map<String, String> result = new HashMap<>();
        result.put("trait", traits.getTraitName());
        result.put("description", traits.getDescription());

        return result;

    }

    public Map<String, String> editPersonality(Integer priId, Personality updatedPersonality) {
        Personality personality = personalityRepository.findById(priId).get();
        personality.setUserId(updatedPersonality.getUserId());
        personality.setQnCategory(updatedPersonality.getQnCategory());
        personality.setPersonalityType(updatedPersonality.getPersonalityType());
        Date now = new Date();
        personality.setDateTime(now);
        personalityRepository.save(personality);

        Traits traits = traitsService.getTraitByPersonalityType(personality.getPersonalityType());
        Map<String, String> result = new HashMap<>();
        result.put("trait", traits.getTraitName());
        result.put("description", traits.getDescription());

        return result;
    }

    public void deletePersonality(Integer priId) {
        personalityRepository.deleteById(priId);
    }
}

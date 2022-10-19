package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.PersonalityRepository;
import net.minidev.json.JSONObject;
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

    public Map<String, Boolean> checkTestDone(Integer userId) {
        List<Personality> list = personalityRepository.findByUserId(userId);
        Map<String, Boolean> response = new HashMap<>();
        for (int i=0; i<list.size(); i++) {
            Personality personality = list.get(i);
            if (personality.getQnCategory().equals("16Personalities")) {
                response.put("16Personalities", true);
            }
            if (personality.getQnCategory().equals("Love")) {
                response.put("Love", true);
            }
            if (personality.getQnCategory().equals("Job")) {
                response.put("Job", true);
            }

        }
        return response;
    }

    public Map<String, String> addNewPersonality(Personality personality) {
        Date now = new Date();
        Personality newPersonality = new Personality(personality.getUserId(), personality.getQnCategory(), personality.getPersonalityType(), now);
        personalityRepository.save(newPersonality);
        Traits traits = traitsService.getTraitByPersonalityType(personality.getPersonalityType());
        Map<String, String> result = new HashMap<>();
        result.put("personalityType", personality.getPersonalityType());
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
        result.put("personalityType", personality.getPersonalityType());
        result.put("trait", traits.getTraitName());
        result.put("description", traits.getDescription());

        return result;
    }

    public void deletePersonality(Integer priId) {
        personalityRepository.deleteById(priId);
    }
}

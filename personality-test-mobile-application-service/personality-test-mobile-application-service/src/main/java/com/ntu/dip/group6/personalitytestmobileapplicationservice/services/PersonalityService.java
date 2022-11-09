package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Personality;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.User;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.PersonalityRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class PersonalityService {

    @Autowired
    PersonalityRepository personalityRepository;

    @Autowired
    TraitsService traitsService;

    @Autowired
    UserService userService;

    public Iterable<Personality> getAllPersonalities() {
        return personalityRepository.findAll();
    }

    public Personality getPersonalityById(Integer priId) {
        return personalityRepository.findById(priId).get();
    }

    public List<Personality> getPersonalityByUserId(Integer userId) {
        return personalityRepository.findByUserId(userId);
    }

    public Personality getPersonalityByUserIdLatest(String qnCategory, Integer userId) {
        List<Personality> list = personalityRepository.findByUserId(userId);
        List<Personality> filteredByCategory = new ArrayList<>();

        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getQnCategory().toLowerCase().equals(qnCategory.toLowerCase())) {
                filteredByCategory.add(list.get(i));
            }
        }

        return filteredByCategory.get(filteredByCategory.size()-1);
    }

    public List<Personality> getPersonalityListLatest(String qnCategory) {

        Iterable<Personality> personalities = personalityRepository.findAll();
        List<Integer> userIdList = new ArrayList<>();
        List<Personality> filteredLatest = new ArrayList<>();

        for (Personality personality : personalities) {
            if (personality.getQnCategory().toLowerCase().equals(qnCategory.toLowerCase()) && !userIdList.contains(personality.getUserId())) {
                userIdList.add(personality.getUserId());
            }
        }

        for (Integer userId : userIdList) {
            filteredLatest.add(getPersonalityByUserIdLatest(qnCategory, userId));
        }

        return filteredLatest;
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

    public List<User> getCompatibility(String qnCategory, String type) {

        List<User> output = new ArrayList<>();

        for (Personality latestPersonality : getPersonalityListLatest(qnCategory)) {
            Integer count = 0;
            for (int i=0; i<type.length(); i++) {
                char ch = type.charAt(i);
                if (latestPersonality.getPersonalityType().contains(String.valueOf(ch))) {
                    count+=1;
                }
            }

            if (count >= 2 && !output.contains(userService.getUserById(latestPersonality.getUserId()))) {
                output.add(userService.getUserById(latestPersonality.getUserId()));
            }
        }

        return output;
    }

}


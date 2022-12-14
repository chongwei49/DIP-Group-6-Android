package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Question;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.TraitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraitsService {

    @Autowired
    TraitsRepository traitsRepository;

    public Iterable<Traits> getAllTraits() {
        return traitsRepository.findAll();
    }

    public Traits getTraitByName(String traitName) {
        return traitsRepository.findByTraitName(traitName);
    }

    public Traits getTraitByPersonalityType(String personalityType) {
        return traitsRepository.findByPersonalityType(personalityType);
    }

    public void addNewTrait(Traits traits) {
        Traits newTrait = new Traits(traits.getPriId(), traits.getQuizCategory(), traits.getPersonalityType(), traits.getTraitName(), traits.getDescription());
        traitsRepository.save(newTrait);
    }

    public void editTrait(Integer priId, Traits updatedTrait) {
        Traits trait = traitsRepository.findById(priId).get();
        trait.setQuizCategory(updatedTrait.getQuizCategory());
        trait.setPersonalityType(updatedTrait.getPersonalityType());
        trait.setTraitName(updatedTrait.getTraitName());
        trait.setDescription(updatedTrait.getDescription());
        traitsRepository.save(trait);
    }

    public void deleteTrait(Integer priId) {
        traitsRepository.deleteById(priId);
    }

}

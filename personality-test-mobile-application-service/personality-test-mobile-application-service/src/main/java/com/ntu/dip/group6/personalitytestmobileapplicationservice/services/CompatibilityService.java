//package com.ntu.dip.group6.personalitytestmobileapplicationservice.services;
//
//import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Compatibility;
//import com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories.CompatibiltyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CompatibilityService {
//
//    @Autowired
//    CompatibiltyRepository compatibiltyRepository;
//
//    public Iterable<Compatibility> getAllCompatibilities() {
//        return compatibiltyRepository.findAll();
//    }
//
//    public void addNewCompatibility(Compatibility compatibility) {
//        Compatibility newCompatibility = new Compatibility(compatibility.getCompatibilityId(), compatibility.getUserId(), compatibility.getQnCategory(), compatibility.getQnId(), compatibility.getQns(), compatibility.getAnswer(), compatibility.getResponded());
//        compatibiltyRepository.save(newCompatibility);
//    }
//
//    public void editCompatibility(Integer priId, Compatibility updatedCompatibility) {
//        Compatibility compatibility = compatibiltyRepository.findById(priId).get();
//
//        compatibility.setUserId(updatedCompatibility.getUserId());
//        compatibility.setQnCategory(updatedCompatibility.getQnCategory());
//        compatibility.setQnId(updatedCompatibility.getQnId());
//        compatibility.setQns(updatedCompatibility.getQns());
//        compatibility.setAnswer(updatedCompatibility.getAnswer());
//        compatibility.setResponded(updatedCompatibility.getResponded());
//
//        compatibiltyRepository.save(compatibility);
//
//    }
//
//    public void deleteCompatibility(Integer priId){
//        compatibiltyRepository.deleteById(priId);
//    }
//
//}

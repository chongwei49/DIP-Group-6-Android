package com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Traits;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraitsRepository extends CrudRepository<Traits, Integer> {

    public List<Traits> findByQuizCategory(String quizCategory);
    public Traits findByTrait(String trait);
}

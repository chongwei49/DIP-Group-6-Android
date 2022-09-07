package com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

    public List<Question> findByQnId(Integer qnId);
    public List<Question> findByQnCategory(String qnCategory);
}

package com.ntu.dip.group6.personalitytestmobileapplicationservice.repositories;

import com.ntu.dip.group6.personalitytestmobileapplicationservice.models.Compatibility;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompatibiltyRepository extends CrudRepository<Compatibility, Integer> {

    public List<Compatibility> findByCompatibilityId(Integer compatibilityId);
}

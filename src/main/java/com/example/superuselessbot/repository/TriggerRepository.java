package com.example.superuselessbot.repository;

import com.example.superuselessbot.model.Trigger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggerRepository extends CrudRepository<Trigger, Integer> {
}

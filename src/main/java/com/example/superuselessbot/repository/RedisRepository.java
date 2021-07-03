package com.example.superuselessbot.repository;

import com.example.superuselessbot.model.Trigger;

import java.util.Map;

public interface RedisRepository {
    Map<Object, Object> findAllMovies();
    void add(Trigger movie);
    void delete(String id);
    Trigger findMovie(String id);
}

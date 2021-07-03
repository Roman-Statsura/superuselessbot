package com.example.superuselessbot.repository;

import com.example.superuselessbot.model.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {
    private static final String KEY = "Trig";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(final Trigger trigger) {
        hashOperations.put(KEY, trigger.getId(), trigger.getFigi());
    }

    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }

    public Trigger findMovie(final String id){
        return (Trigger) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> findAllMovies(){
        return hashOperations.entries(KEY);
    }


}

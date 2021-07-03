package com.example.superuselessbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@RedisHash("trigger")
@Getter
@Setter
public class Trigger implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String figi;

    public Trigger(String figi){
        this.figi = figi;
    }
}

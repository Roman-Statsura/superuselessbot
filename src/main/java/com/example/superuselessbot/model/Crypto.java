package com.example.superuselessbot.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Crypto implements Serializable {
    private Integer id;
    private Integer price;
    private String figi;
}

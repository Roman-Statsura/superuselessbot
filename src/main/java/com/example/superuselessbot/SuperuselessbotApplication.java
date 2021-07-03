package com.example.superuselessbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SuperuselessbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperuselessbotApplication.class, args);
    }

}

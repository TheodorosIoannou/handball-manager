package com.handball.manager;

import com.handball.manager.service.EventService;
import com.handball.manager.service.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HandballManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandballManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(PlayerService playerService, EventService eventService) {
        return args -> {
            playerService.initSampleData();
            eventService.initSampleData();
            System.out.println("Sample data initialized");
        };
    }
}
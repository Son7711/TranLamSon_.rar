package com.example.baitapbuoi2;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class Baitapbuoi2Application {
    public static void main(String[] args) {
        SpringApplication.run(Baitapbuoi2Application.class, args);
    }
}
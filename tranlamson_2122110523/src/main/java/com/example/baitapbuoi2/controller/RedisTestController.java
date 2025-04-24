package com.example.baitapbuoi2.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {
   private final StringRedisTemplate redisTemplate;
   public RedisTestController(StringRedisTemplate redisTemplate) {
       this.redisTemplate = redisTemplate;
   }
   @GetMapping("/test-redis")
   public String testRedis() {
       try {
           redisTemplate.opsForValue().set("testKey", "Hello Redis!");
           return "Redis connected: " + redisTemplate.opsForValue().get("testKey");
       } catch (Exception e) {
           return "Failed to connect to Redis: " + e.getMessage();
       }
   }
}

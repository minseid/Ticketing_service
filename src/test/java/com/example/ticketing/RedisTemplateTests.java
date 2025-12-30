package com.example.ticketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisTemplateConfig {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    void redisTemplateTest() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "name";
        valueOperations.set(key,"giraffe");
        String value = valueOperations.get(key);
        assertEquals("giraffe", value);
    }
}
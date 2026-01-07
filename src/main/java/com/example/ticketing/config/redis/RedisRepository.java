package com.example.ticketing.config.redis;

import com.example.ticketing.user.User;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<User, String> {
}

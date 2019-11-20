package com.redis.cache.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.cache.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
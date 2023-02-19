package com.redis.cache.demo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cache.demo.model.User;
import com.redis.cache.demo.repository.UserRepository;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unchecked")
	@Cacheable(value = "users", key = "#userId", unless = "#result.age > 30")//unless age > 30 means... age < 30 will be stored in cache
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") Long userId) {
		LOG.info("Getting user with ID {}.", userId);
		LOG.info("Started to wait for 10 sec");
		try {
			for (int i = 0; i < 10;) {
				Thread.sleep(100L);
				LOG.info(i++ + "...");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("10 sec wait ended!!");
		Optional<User> user =  userRepository.findById(userId);
		return user.isPresent()?user.get():new User();
	}

	@CachePut(value = "users", key = "#user.id")
	@PutMapping("/")
	public User updatePersonByID(@RequestBody User user) {
		userRepository.save(user);
		LOG.info("Started to wait for 10 sec");
		try {
			for (int i = 0; i < 10;) {
				Thread.sleep(100L);
				LOG.info(i++ + "...");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("10 sec wait ended!!");
		return user;
	}

	@CacheEvict(value = "users", allEntries = true)
	@DeleteMapping("/{userId}")
	public void deleteUserByID(@PathVariable Long userId) {
		LOG.info("deleting person with id {}", userId);
		LOG.info("Started to wait for 10 sec");
		try {
			for (int i = 0; i < 10;) {
				Thread.sleep(100L);
				LOG.info(i++ + "...");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.info("10 sec wait ended!!");
		userRepository.deleteById(userId);
	}

}

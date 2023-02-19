package com.redis.cache.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.redis.cache.demo.model.User;
import com.redis.cache.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableCaching
public class RedisCacheDemoApplication implements CommandLineRunner {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	//@Autowired
	private final UserRepository userRepository;

	@Autowired
	public RedisCacheDemoApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedisCacheDemoApplication.class, args);
	}

	
	public void run(String... args) throws Exception {
		
		//Populating embedded database here
	    LOG.info("Saving users. Current user count is {}.", userRepository.count());
	    User sriram = new User("Srirma", 24L);
	    User eric = new User("Eric", 40L);
	    User lewis = new User("Lewis", 29L);

	    userRepository.save(sriram);
	    userRepository.save(eric);
	    userRepository.save(lewis);
	    LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	}
}

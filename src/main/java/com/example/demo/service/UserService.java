package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	 public User findByUsername(String username) {
	    return userRepository.findByUsername(username).orElse(null); 
	}
	
	 public User findByEmail(String email) {
	    return userRepository.findByEmail(email).orElse(null);  // Optional<User> döner, null dönebilir
	}
	
	 public void saveUser(User user) {
	    userRepository.save(user);
    }
	
	
}

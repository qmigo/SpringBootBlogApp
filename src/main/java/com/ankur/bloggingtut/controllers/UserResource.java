package com.ankur.bloggingtut.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.bloggingtut.payloads.UserDto;
import com.ankur.bloggingtut.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	UserServiceImpl service;
	
	public UserResource(UserServiceImpl service) {
		super();
		this.service = service;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
		UserDto user = service.getUserById(userId);
		return new ResponseEntity<UserDto> (user, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = service.getAllUsers();
		return new ResponseEntity<List<UserDto>> (users, HttpStatus.OK);
	}


	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user_details) {
		UserDto user = service.createUser(user_details);
		return new ResponseEntity<UserDto> (user, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user_details, @PathVariable Integer userId) {
		UserDto user = service.updateUser(user_details, userId);
		return new ResponseEntity<UserDto> (user, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
		service.deleteUser(userId);
		return new ResponseEntity<String> ("Resource Deleted", HttpStatus.OK);		
	}
}

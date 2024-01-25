package com.ankur.bloggingtut.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ankur.bloggingtut.entities.User;
import com.ankur.bloggingtut.exceptions.ResourceNotFoundException;
import com.ankur.bloggingtut.payloads.UserDto;
import com.ankur.bloggingtut.repositories.UserRepository;
import com.ankur.bloggingtut.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userDao;
	

	public UserServiceImpl(UserRepository userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public UserDto createUser(UserDto user) {
		User savedUser = this.userDao.save(this.dtoToUser(user));
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user_updates, Integer userId) {
		User user = userDao
					.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
//		if(user_updates.getName()!=null) {
//			user.setName(user_updates.getName());
//		}
//		if(user_updates.getBio()!=null) {
//			user.setBio(user_updates.getBio());
//		}
//		if(user_updates.getEmail()!=null) {
//			user.setEmail(user_updates.getEmail());
//		}
//		if(user_updates.getPassword()!=null) {
//			user.setPassword(user_updates.getPassword());
//		}
		
		Optional.ofNullable(user_updates.getName()).ifPresent(user::setName);
		
		Optional.ofNullable(user_updates.getBio()).ifPresent(user::setBio);
		
		Optional.ofNullable(user_updates.getEmail()).ifPresent(user::setEmail);
		
		Optional.ofNullable(user_updates.getPassword()).ifPresent(user::setPassword);


		User updated_user = userDao.save(user);
		
		return this.userToDto(updated_user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userDao
						.findById(userId)
						.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userDao.findAll();
		return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userDao
				.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		userDao.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setBio(userDto.getBio());		
		return user;
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setBio(user.getBio());
		
		return userDto;
	}
	

}

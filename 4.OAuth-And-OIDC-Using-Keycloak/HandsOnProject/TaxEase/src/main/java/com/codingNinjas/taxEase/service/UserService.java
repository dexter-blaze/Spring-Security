package com.codingNinjas.taxEase.service;

import com.codingNinjas.taxEase.dto.UserDto;
import com.codingNinjas.taxEase.exception.UserNotFoundException;
import com.codingNinjas.taxEase.model.Role;
import com.codingNinjas.taxEase.model.User;
import com.codingNinjas.taxEase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public void createUser(UserDto userDto) {
		BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
		Role role = new Role();
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setPassword(encodedPassword);
		user.setAge(userDto.getAge());
		user.setEmail(userDto.getEmail());
		if(userDto.getRole().toUpperCase().contains("NORMAL")){
			role.setRoleName("ROLE_NORMAL");
		} else if (userDto.getRole().toUpperCase().contains("ADMIN")){
			role.setRoleName("ROLE_ADMIN");
		} else{
			role.setRoleName("ROLE_NORMAL");
		}
		Set<Role> rolesSet = new HashSet<>();
		rolesSet.add(role);
		user.setRoles(rolesSet);
		userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void updateUser(UserDto userDto, Long id) {
		User updateUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User Not Found"));
		if(userRepository.existsById(id)){
			BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
			if(userDto.getAge()!= 0){
				updateUser.setAge(userDto.getAge());
			}
			if(!userDto.getPassword().isBlank()){
				updateUser.setPassword(encodedPassword);
			}
			if(!userDto.getUsername().isBlank()){
				updateUser.setUsername(userDto.getUsername());
			}
			userRepository.save(updateUser);
		}
	}

	public User getUserById(Long userid) {
		return userRepository.findById(userid).
				orElseThrow(()-> new UserNotFoundException("User not found for id: " + userid));
	}
}

package com.codingNinjas.taxEase.controller;

import com.codingNinjas.taxEase.dto.UserDto;
import com.codingNinjas.taxEase.model.User;
import com.codingNinjas.taxEase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userid}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
	public User getUserById(@PathVariable Long userid) {
		return userService.getUserById(userid);
	}

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('NORMAL') or hasAuthority('NORMAL')")
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(@PathVariable Long id,@RequestBody UserDto userDto) {
		userService.updateUser(userDto, id);
	}
}

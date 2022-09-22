package com.tsystems.mms.demoapp.user;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful API controller for managing users.
 */
@RestController
@RequestMapping("/api/v1.0")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Finds all users.
	 * 
	 * @return A list of users.
	 */
	@GetMapping("/user")
	public ResponseEntity getUsers() {

		LOGGER.info("Get all users from the database");
		return ResponseEntity.ok(userService.getAll());
	}

	@GetMapping("/user/{userid}")
	public ResponseEntity<User> getUserById(@PathVariable("userid") Long userId) {
		LOGGER.info("Retriving user with id " + userId);
		User user = userService.getUserById(userId);
		LOGGER.info("User returned " + user.toString());
		return ResponseEntity.ok(user);
	}

	@PostMapping("/user")
	public ResponseEntity<URI> saveUser(@RequestBody UserCreateCommand command) {
		LOGGER.info("Creating new user");
		User userSaved = userService.saveUser(command);
		LOGGER.info("Created new user: " + userSaved.toString());
		return ResponseEntity.created(URI.create("/api/v1.0/user/" + userSaved.getId())).build();
	}

	@PutMapping("/user/{userid}")
	public ResponseEntity<Void> updateUser(@RequestBody UserUpdateCommand command,
			@PathVariable("userid") Long userId) {
		LOGGER.info("Updating user with id " + userId);
		userService.updateUser(userId, command);
		LOGGER.info("User updated with id " + userId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/user/{userid}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userid") Long userId) {
		LOGGER.info("Deleting user with id " + userId);
		userService.deleteUser(userId);
		LOGGER.info("User deleted with id " + userId);
		return ResponseEntity.noContent().build();
	}

}
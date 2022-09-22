package com.tsystems.mms.demoapp.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.mms.demoapp.exceptionhandling.UserNotFoundException;

import net.bytebuddy.implementation.bytecode.constant.MethodConstant.CanCache;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * This service manages all user.
 */
@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Find all users from the database.
	 * 
	 * @return List of users.
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Can not find user with id" + id));
	}

	public void updateUser(Long userId, UserUpdateCommand command) {
		User userToUpdate = this.getUserById(userId);
		userToUpdate.setEmail(command.getEmail());
	}

	public void deleteUser(Long userId) {
		User userToDelete = this.getUserById(userId);
		userRepository.delete(userToDelete);
	}
}

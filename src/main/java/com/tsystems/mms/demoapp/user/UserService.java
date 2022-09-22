package com.tsystems.mms.demoapp.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.mms.demoapp.exceptionhandling.UserNotFoundException;

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

	public User saveUser(UserCreateCommand command) {
		User user = mapCommandToUser(command);
		User userSaved = userRepository.save(user);
		return userSaved;
	}

	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Can not find user with id" + id));
	}

	public void updateUser(Long userId, UserUpdateCommand command) {
		User userToUpdate = this.getUserById(userId);
		if (command.getEmail() != null) {
			userToUpdate.setEmail(command.getEmail());
		}
		if (command.getFirstname() != null) {
			userToUpdate.setFirstName(command.getFirstname());
		}
		if (command.getSurname() != null) {
			userToUpdate.setSurName(command.getSurname());
			;
		}
		if (command.getGender() != null) {
			userToUpdate.setGender(command.getGender());
		}

	}

	public void deleteUser(Long userId) {
		User userToDelete = this.getUserById(userId);
		userRepository.delete(userToDelete);
	}

	public User mapCommandToUser(UserCreateCommand command) {
		User user = new User();
		user.setEmail(command.getEmail());
		user.setFirstName(command.getFirstname());
		user.setSurName(command.getSurname());
		user.setGender(command.getGender());
		return user;
	}
}

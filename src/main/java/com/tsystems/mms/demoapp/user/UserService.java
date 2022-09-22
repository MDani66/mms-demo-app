package com.tsystems.mms.demoapp.user;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.mms.demoapp.exceptionhandling.InvalidEmailException;
import com.tsystems.mms.demoapp.exceptionhandling.UserNotFoundException;
import com.tsystems.mms.demoapp.orgunit.OrgUnitService;

/**
 * This service manages all user.
 */
@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final OrgUnitService unitService;

	public UserService(UserRepository userRepository, OrgUnitService unitService) {
		this.userRepository = userRepository;
		this.unitService = unitService;
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
			if (userEmailValidation(command.getEmail())) {
				userToUpdate.setEmail(command.getEmail());
			} else {
				throw new InvalidEmailException("Invalid email");
			}

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
		if (command.getUnitId() != null) {
			OrganisationalUnit orgUnit = getOrganisationalUnit(command.getUnitId());
			userToUpdate.setOrganisationalUnit(orgUnit);
		}

	}

	public void deleteUser(Long userId) {
		User userToDelete = this.getUserById(userId);
		userRepository.delete(userToDelete);
	}

	public User mapCommandToUser(UserCreateCommand command) {
		User user = new User();
		if (userEmailValidation(command.getEmail())) {
			user.setEmail(command.getEmail());
		} else {
			throw new InvalidEmailException("Invalid email");
		}
		user.setFirstName(command.getFirstname());
		user.setSurName(command.getSurname());
		user.setGender(command.getGender());
		OrganisationalUnit orgUnit = getOrganisationalUnit(command.getUnitId());
		user.setOrganisationalUnit(orgUnit);
		return user;
	}

	public boolean userEmailValidation(String email) {
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPattern.matcher(email);
		return matcher.find();
	}
	
	public OrganisationalUnit getOrganisationalUnit(Long id) {
		Optional<OrganisationalUnit> orgUnit = unitService.getOrgUnit(id);
		return orgUnit.get();
	}

}

package com.tsystems.mms.demoapp.user;

public class UserCreateCommand {
	
	private String email;
	
	private String firstname;
	
	private String surname;
	
	private Gender gender;

	/**
	 * 
	 */
	public UserCreateCommand() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	

}

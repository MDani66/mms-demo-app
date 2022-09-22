package com.tsystems.mms.demoapp.user;

public class UserUpdateCommand {
	
	private String email;

	/**
	 * @param email
	 */
	public UserUpdateCommand(String email) {
		super();
		this.email = email;
	}

	/**
	 * 
	 */
	public UserUpdateCommand() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

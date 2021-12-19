package com.springsecurityjwt.springsecurityjwt.service;

public class AutherizationRequestDto {

	private String userName;
	private String password;

	/**
	 * 
	 */
	public AutherizationRequestDto() {
		super();
	}

	/**
	 * @param userName
	 * @param password
	 */
	public AutherizationRequestDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

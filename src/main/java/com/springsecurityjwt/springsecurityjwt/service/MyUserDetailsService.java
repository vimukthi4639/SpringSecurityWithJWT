package com.springsecurityjwt.springsecurityjwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return new User("vimukthi", "$2a$10$UYqmeZg3iDmyKng8mQCclOMOvjK1a4mWUxUh5LOvu5/kDLQnVIox6", new ArrayList<>());
	}

}

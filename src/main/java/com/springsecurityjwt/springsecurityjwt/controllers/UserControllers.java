package com.springsecurityjwt.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityjwt.springsecurityjwt.service.AutherizationRequestDto;
import com.springsecurityjwt.springsecurityjwt.service.AutherizationResponseDto;
import com.springsecurityjwt.springsecurityjwt.service.JwtUtil;
import com.springsecurityjwt.springsecurityjwt.service.MyUserDetailsService;

@RestController()
//@RequestMapping("/test")
public class UserControllers {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@GetMapping("/user")
	public String getUser() {
		return "Hi User";
	}

	@PostMapping("/autherize")
	public AutherizationResponseDto autherize(@RequestBody AutherizationRequestDto requestDto) {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword()));

		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("USer anme not found");
		}
		UserDetails userDetails = userDetailsService.loadUserByUsername(requestDto.getUserName());

		if (userDetails != null) {

			// create jwt token
			String jwt = jwtUtil.createJWTToken(userDetails);

			AutherizationResponseDto autherizationResponseDto = new AutherizationResponseDto();
			autherizationResponseDto.setJwtToken(jwt);

			return autherizationResponseDto;

		} else {
			throw new UsernameNotFoundException("User Name Not Found");
		}

	}

}

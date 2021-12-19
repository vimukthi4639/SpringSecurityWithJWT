package com.springsecurityjwt.springsecurityjwt.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springsecurityjwt.springsecurityjwt.service.JwtUtil;
import com.springsecurityjwt.springsecurityjwt.service.MyUserDetailsService;

@Component
public class FilterAuthentication extends OncePerRequestFilter {

	@Autowired
	MyUserDetailsService myUserServiceDetails;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header == null) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.replace("Bearer ", "");

		String userName = jwtUtil.getUserName(token);
		UserDetails userDetails = myUserServiceDetails.loadUserByUsername(userName);

		boolean isValid = jwtUtil.isValidToken(token, userDetails);

		if (isValid && SecurityContextHolder.getContext().getAuthentication() == null) {
			UsernamePasswordAuthenticationToken userNamePasswordTokne = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			userNamePasswordTokne.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(userNamePasswordTokne);

		}

		filterChain.doFilter(request, response);

	}

}

package com.springsecurityjwt.springsecurityjwt.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	public String createJWTToken(UserDetails userDetails) {

		return Jwts.builder().setClaims(new HashMap<>()).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, "123jkajfdurowie").compact();

	}

	public boolean isValidToken(String token, UserDetails userDetails) {

		String jwt = Jwts.parser().setSigningKey("123jkajfdurowie").parseClaimsJws(token).getBody().getSubject();
		if (jwt.equals(userDetails.getUsername())) {
			return true;
		} else {
			return false;
		}

	}

	public String getUserName(String token) {
		String jwt = Jwts.parser().setSigningKey("123jkajfdurowie").parseClaimsJws(token).getBody().getSubject();

		return jwt;

	}

}

package com.springsecurityjwt.springsecurityjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);


		String as = "abc5fgrt5445trgf5cba";
		
		String s = new StringBuilder(as).reverse().toString();
		
		String abc = s.substring(0, s.length()/2);
		String cc = as.substring(0,as.length()/2);
		System.out.println(abc.equals(cc));
		
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		return new BCryptPasswordEncoder();
	}

}

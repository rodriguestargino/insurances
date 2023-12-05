package com.audsat.insurances;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.audsat.insurances.config.SecurityConfiguration;

@SpringBootApplication
@EnableWebSecurity(debug = false)
public class InsurancesApplication {
	
	@Autowired
    private SecurityConfiguration securityConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(InsurancesApplication.class, args);
	}

	
}

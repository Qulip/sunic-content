package com.sunic.content.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the Content Service.
 * This follows the modularization-strategy.md pattern for global configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authz -> authz
				.requestMatchers("/api/v1/**").permitAll()
				.requestMatchers("/actuator/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().authenticated()
			);

		return http.build();
	}
}
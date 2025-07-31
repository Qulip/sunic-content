package com.sunic.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for Content Service.
 * This follows the modularization-strategy.md pattern for application bootstrap.
 */
@SpringBootApplication(scanBasePackages = "com.sunic.content")
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }
}
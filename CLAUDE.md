# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot-based content management microservice following a modular architecture pattern. The service manages categories, content, and lectures with a clean separation of concerns across multiple modules.

## Module Architecture

The project follows a 4-layer modular architecture:

- **content-spec**: Domain contracts and specifications (interfaces, DTOs, exceptions)
- **content-aggregate**: Business logic and data access layer (JPA entities, repositories, domain logic)
- **content-rest**: REST API and web layer (controllers, request/response handling)
- **content-boot**: Application bootstrap and runtime configuration

Module dependencies flow: `content-boot` → `content-rest` → `content-aggregate` → `content-spec`

## Key Patterns

### CQRS Pattern
The service uses CQRS naming conventions:
- **Cdo**: Command Data Objects (create operations)
- **Udo**: Update Data Objects (update operations) 
- **Qdo**: Query Data Objects (search/filter operations)
- **Rdo**: Response Data Objects (read operations)

### Facade Pattern
Domain operations are exposed through facade interfaces in the spec module and implemented in the aggregate layer.

## Development Commands

### Build and Run
```bash
# Build the entire project
./gradlew build

# Run the application (port 8080)
./gradlew :content-boot:bootRun

# Build specific module
./gradlew :content-spec:build
```

### Testing
```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :content-aggregate:test

# Run tests with JUnit platform
./gradlew test --info
```

### Database Setup
The application expects a MySQL database:
- **Local**: `jdbc:mysql://localhost:3306/sunic` (username: mysuni, password: mysuni)
- **Production**: Uses environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`

## Core Domain Entities

### Category
- Hierarchical category system for organizing content
- Has validation for category-lecture relationships

### Content
- Media content with URL, type (VIDEO/DOCUMENT), and state management
- State transitions: DRAFT → PUBLISHED → ARCHIVED

### Lecture
- Educational content with difficulty levels, learning types, and categories
- Complex entity with multiple enum types and state management

## API Documentation

Swagger UI is available at `/swagger-ui.html` when the application is running.

## Configuration Profiles

- **local**: Development profile with SQL logging enabled
- **prod**: Production profile using environment variables

## Security

The application uses JWT-based authentication with Spring Security. JWT secret key is configured in `application.yml`.
# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot microservice for content management following a hexagonal architecture pattern. The service manages lectures, categories, and content with a multi-module Gradle structure.

## Common Development Commands

### Building and Running
```bash
# Build the entire project
./gradlew build

# Run the application (from content-boot module)
./gradlew :content-boot:bootRun

# Create bootable JAR
./gradlew :content-boot:bootJar
```

### Testing
```bash
# Run all tests
./gradlew test

# Run tests for specific module
./gradlew :content-spec:test
./gradlew :content-aggregate:test
./gradlew :content-rest:test

# Run tests with JUnit platform
./gradlew test --tests "com.sunic.content.spec.lecture.*"
```

### Database
- MySQL database required (localhost:3306/sunic by default)
- Default credentials: mysuni/mysuni
- JPA DDL auto-update enabled for local development
- Hibernate batching configured for performance

## Module Architecture

The project follows a strict modular architecture:

### content-spec
- **Purpose**: Domain contracts and specifications
- **Contains**: Pure domain entities, exceptions, facades, and SDOs/RDOs
- **Dependencies**: None (pure domain layer)
- **Key Pattern**: Entities are immutable with Lombok builders

### content-aggregate
- **Purpose**: Business logic and data access layer
- **Contains**: JPA entities (JPOs), repositories, and business logic
- **Dependencies**: content-spec, Spring Data JPA, MySQL connector
- **Key Pattern**: Logic classes contain business rules, Store classes handle persistence

### content-rest
- **Purpose**: REST API and web presentation layer
- **Contains**: REST controllers (Resources), DTOs, exception handlers
- **Dependencies**: content-spec, Spring Web
- **Key Pattern**: Controllers use Facade pattern with SDO/RDO conversion

### content-boot
- **Purpose**: Application bootstrap and runtime configuration
- **Contains**: Main application class, security config, Swagger config
- **Dependencies**: All other modules
- **Key Pattern**: Executable JAR with full application context

### content-client (referenced but not present)
- **Purpose**: External service integration clients
- **Pattern**: Circuit breaker configuration present in application.yml

## Development Conventions

### Package Structure
- Follow `com.sunic.content.{module}.{domain}.{layer}` pattern
- Domains: category, content, lecture, student
- Layers: entity, exception, facade, logic, store, repository, dto, rest

### Data Transfer Patterns
- **Request/Response DTOs**: REST layer data contracts
- **SDO (Service Data Object)**: Inbound data to facade layer
- **RDO (Result Data Object)**: Outbound data from facade layer
- **JPO (JPA Persistence Object)**: Database entity representations

### API Response Pattern
All REST endpoints return standardized `ApiResponse<T>` wrapper with success/error structure.

### Entity Design
- Domain entities (content-spec) are immutable with Lombok builders
- JPA entities (content-aggregate) handle persistence concerns
- Clear separation between domain and persistence models

## Configuration Profiles

### Local Development (`local` profile - default)
- MySQL on localhost:3306
- Debug logging enabled
- SQL logging enabled
- JWT secret key configured

### Production (`prod` profile)
- Environment variable based configuration
- DB_URL, DB_USERNAME, DB_PASSWORD
- Minimal logging (WARN level)

## Key Features

### Resilience
- Circuit breaker configured for content-service client
- 50% failure rate threshold, 30s open state duration

### Monitoring
- Actuator endpoints: health, info, metrics
- Health details shown when authorized

### Documentation
- Swagger UI available at `/swagger-ui.html`
- API docs at `/v3/api-docs`

## Testing Strategy

- JUnit 5 with AssertJ for assertions
- Testcontainers for integration testing with MySQL
- Spring Boot Test integration
# Midas
Project repo for the JPMC Advanced Software Engineering Forage program

---
## Progress Log

### Task 1: Environment Setup & Configuration
**Status:** ✅ Success

**Objectives:** Initialize the local development environment, configure the project scaffold, and verify the build pipeline.

### Steps Taken:
1. **Environment Verification:** Confirmed local machine is running **Java 17 (LTS)**.
2. **Version Control:** Forked the repository to personal GitHub and cloned the `flow` branch locally.
3.  **Dependency Management:** Updated `pom.xml` to include critical Spring Boot starters and infrastructure libraries (see below).
4.  **Configuration:** Updated `application.yml` to define the Kafka topic `trader-updates`.
5.  **Verification:** Successfully ran `mvn -Dtest=TaskOneTests test` to validate the environment.

#### Dependencies Added
We added specific libraries to `pom.xml` to support the Event-Driven Architecture (EDA) requirements:
| Dependency | Purpose |
| :--------- | :------ |
| **`spring-kafka`** | Provides high-level abstractions for Kafka. It allows us to build a `KafkaListener` to consume messages from the `trader-updates` topic without writing low-level boilerplate code. |
| **`spring-boot-starter-data-jpa`** | Incorporates Hibernate and Spring Data. This allows us to map our Java objects (Entities) to database tables and perform SQL operations using simple Repository interfaces. |
| **`h2`** | An in-memory relational database. We use this for development and testing because it spins up instantly and resets cleanly on every restart, removing the need for a dedicated external database server. |
| **`spring-boot-starter-web`** | Adds the embedded Tomcat server and Spring MVC. This is required to expose the REST endpoints (Task 5) and handle JSON serialization/deserialization. |
| **`spring-kafka-test` & `testcontainers`** | Testing utilities that allow us to spin up a "real" Kafka broker inside a Docker container during our test phase, ensuring our integration tests are accurate. |
---
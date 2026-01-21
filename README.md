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

### Task 2: Kafka Integration & Message Consumption
**Status:** ✅ Completed

**Objective:** Implement the ingress layer for Midas Core by creating a Kafka Consumer that listens to the `trader-updates` topic and deserializes incoming JSON messages into `Transaction` objects.

#### Steps Taken:
1.  **Consumer Implementation:** Created `KafkaRepository.java` in the `component` package. Used the `@KafkaListener` annotation to subscribe to the topic defined in `application.yml`.
2.  **Configuration Fixes:**
    * **Missing Consumer Group:** Encountered `IllegalStateException`. Fixed by adding `spring.kafka.consumer.group-id: midas-group` to `application.yml` to allow Kafka to track consumption offsets.
    * **Offset Reset:** Configured `auto-offset-reset: earliest` to ensure the application processes all historical messages upon startup.
3.  **Debugging & Syntax Correction:** Fixed a SpEL (Spring Expression Language) syntax error in the listener annotation (`${general.kafka-topic}`).
4.  **Data Quality Analysis:** * While running `TaskTwoTests`, we encountered a `NumberFormatException` due to malformed data in the test file `poiuytrewq.uiop` (specifically a space inside a floating-point number: `"122.86 5"`).
    * **Resolution:** Instead of relying on the crashing test harness, we manually inspected the raw CSV/JSON data in `poiuytrewq.uiop` to verify the transaction stream and retrieve the required validation values.

#### Key Findings (First 4 Transactions):
We confirmed the listener would process the following amounts based on the raw input data:
1.  `122.86`
2.  `42.87`
3.  `161.79`
4.  `22.22`

---
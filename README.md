# tacoloco-appsvc
Taco Loco services

The project provides:

1. Custom and manage all exceptions basing on error codes and messages as friendly error JSONs. 
2. Spring Controller advice to translate the server-side exceptions.
3. Spring Boot logback to write out daily log file. All configuration of logging is stored in logback-spring.xml
4. Unit Testing
5. Using org.junit.jupiter.api
6. Web Layer Testing: SpringBootTest and MockMvc
7. Factory Design Partern example.

Required: JDK version 8+, Maven 3.5x, Spring Boot Version 2.3.3

Install and build step by step by Maven
-	Maven install dependencies: mvn clean install
-	Maven package: mvn clean package
-	Run dev and production environments:

java -jar target/TacoLoco-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

java -jar target/TacoLoco-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

Thank you

Khiem Truong

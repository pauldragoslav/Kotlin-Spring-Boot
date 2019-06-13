# Kotlin Spring-Boot
Demo project demonstrating the use of Kotlin and Spring Boot

## Running locally
```
.mvnw clean package
java -jar target\kotlin-demo-0.0.1.jar
```

## Running on Docker
```
docker build -t "kotlin-spring-boot" .
docker run -p 8080:8080 kotlin-spring-boot
```

### Resources
http://localhost:8080/actuator/info
http://localhost:8080/swagger-ui.html

### References
Based upon: https://dzone.com/articles/kotlin-microservice-with-spring-boot

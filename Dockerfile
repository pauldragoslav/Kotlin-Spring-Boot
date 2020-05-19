FROM adoptopenjdk/openjdk14:jdk-14.0.1_7-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/kotlin-demo-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
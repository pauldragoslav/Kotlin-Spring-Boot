FROM adoptopenjdk/openjdk11:jdk-11.0.4_11
VOLUME /tmp
ARG JAR_FILE
COPY target/kotlin-demo-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
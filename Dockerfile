FROM openjdk:17-jdk-alpine
ARG JAR_FILE=room-service/target/*.jar
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
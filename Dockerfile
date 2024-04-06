FROM maven:3.8.1-openjdk-17 as build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17
EXPOSE 8080
COPY --from=build /app/target/*war app.jar
CMD ["java", "-jar", "target/app.jar"]
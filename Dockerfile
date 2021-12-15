FROM openjdk:11-jre
COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=cloud","/app.jar"]
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

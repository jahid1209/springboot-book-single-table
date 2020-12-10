FROM openjdk:8
EXPOSE 8080
ADD target/book-api.jar book-api.jar
ENTRYPOINT ["java","-jar","/book-api.jar"]
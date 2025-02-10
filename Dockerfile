FROM openjdk:17
WORKDIR /app

COPY build/libs/CarrouselCustomerCounter-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
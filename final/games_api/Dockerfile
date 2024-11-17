FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /build
COPY . .
RUN ./mvnw package

FROM eclipse-temurin:17-jre-alpine AS final
WORKDIR /app
COPY --from=build /build/target/*.jar games_api.jar
EXPOSE 8080
CMD ["java", "-jar", "games_api.jar"]
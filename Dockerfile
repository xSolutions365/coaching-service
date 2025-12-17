FROM amazoncorretto:17 AS build
ENV HOME=/usr/app
WORKDIR $HOME
COPY . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean package

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /usr/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

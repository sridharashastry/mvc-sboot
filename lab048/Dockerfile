# Build stage
FROM eclipse-temurin:17-jre-alpine as builder
WORKDIR /app
COPY ./target/hworld-docker-distroless-0.0.1-SNAPSHOT.jar app.jar
RUN apk add --no-cache openjdk17 && \
    mkdir -p /dependencies && cd /dependencies && \
    jar -xf /app/app.jar

# Final stage with minimal JDK
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/app.jar /app/
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

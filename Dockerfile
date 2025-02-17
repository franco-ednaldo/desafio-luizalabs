# Stage 1: Build the Gradle application
FROM gradle:8.5-jdk17 AS builder

WORKDIR /app

# Copia apenas os arquivos essenciais para baixar dependências
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies --no-daemon || true  # Continua mesmo que o gradle.properties não exista

# Copia o código-fonte e constrói a aplicação
COPY src/ src/
RUN gradle build -x test --no-daemon

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR compilado da etapa de build
COPY --from=builder /app/build/libs/process-file-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]


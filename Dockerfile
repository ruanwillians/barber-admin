# Stage 1: Build
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copia os arquivos de dependência para o cache do Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código e faz o build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

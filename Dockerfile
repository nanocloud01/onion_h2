# -------- Etapa de construcción --------
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copia archivos de proyecto y compila
COPY pom.xml .
COPY application ./application
COPY domain ./domain
COPY infrastructure ./infrastructure

RUN ./mvnw clean package

# -------- Etapa de producción --------
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia el jar desde la etapa build
COPY --from=build /app/infrastructure/target/infrastructure-1.0-SNAPSHOT.jar app.jar

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]

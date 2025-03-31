# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY target/gamer-0.0.1-SNAPSHOT.jar app.jar

# Expón el puerto que usará tu aplicación (por defecto Spring Boot usa el puerto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
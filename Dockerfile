# Imagen base con JDK 23
FROM eclipse-temurin:23-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/barcos-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 para acceder a la app
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]

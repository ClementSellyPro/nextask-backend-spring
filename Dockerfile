# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances (pour utiliser le cache Docker)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le reste du code et compiler
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Lancer l'application
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port (Spring Boot utilise 8080 par défaut)
EXPOSE 9000

# Commande pour démarrer
ENTRYPOINT ["java", "-jar", "app.jar"]

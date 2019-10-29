<<<<<<< HEAD
#stage -1  for volume mounting maven dependencies
=======
>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e
FROM maven:3.6-jdk-11 AS build
COPY pom.xml .
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO dependency:go-offline
COPY src src
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO -f pom.xml clean package -DskipTests

#stage -2 for creating the image of the project
FROM openjdk:11
EXPOSE 8182
COPY --from=build /target/csr-service-1.0.0-SNAPSHOT.jar csr-service.jar

ENTRYPOINT ["java", "-jar", "/csr-service.jar"]
<<<<<<< HEAD
=======

>>>>>>> 52dcd7afcdef3aff73473de28d3370b70f6c138e

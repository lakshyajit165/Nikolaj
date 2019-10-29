# stage - 1 building the angular project
FROM node:12-alpine as webapp_build
WORKDIR /webapp
COPY frontend/command-registry-ui /webapp/
RUN npm install
RUN npm run build -- --delete-output-path --output-path=/webapp/dist

#stage - 2 copying the dist folder contents in the static folder of the maven project
FROM maven:3.6-jdk-11 AS build
WORKDIR /appserver
COPY pom.xml /appserver
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO dependency:go-offline
COPY ./src /appserver/src
RUN rm -rf /appserver/src/main/resources/static
RUN mkdir /appserver/src/main/resources/static
COPY --from=webapp_build /webapp/dist/* /appserver/src/main/resources/static/
# RUN mvn clean package -DskipTests
RUN mvn -Dmaven.repo.local=$MVN_LOCAL_REPO -f pom.xml clean package -DskipTests

# stage - 3 creating the maven project
FROM maven:3.6-jdk-11
WORKDIR /appserver
COPY --from=build /appserver/target /appserver/target
ENTRYPOINT ["java", "-jar", "target/command-registry-angular-1.0.0.SNAPSHOT.jar"]


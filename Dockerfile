FROM openjdk:17-alpine

WORKDIR /app

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/project-0.0.1-SNAPSHOT.jar /app/adego.jar

CMD ["java","-jar","/app/adego.jar"]

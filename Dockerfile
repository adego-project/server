FROM openjdk:17-alpine

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/project-0.0.1-SNAPSHOT.jar ${JAR_PATH}/adego.jar

CMD ["java","-jar","./build/libs/adego.jar"]

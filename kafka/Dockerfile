FROM java:8
MAINTAINER kingcall <kingcall@hotmail.com>
EXPOSE 8081
ARG JAR_FILE
ADD target/${JAR_FILE} /weblogreveiver.jar
ENTRYPOINT ["java", "-jar","/weblogreveiver.jar"]

FROM openjdk:8-jdk-slim

MAINTAINER Yagnik "yagnik.bhatt@idexcel.net"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY ./target/admin-service-0.0.1-SNAPSHOT.war webapp.war

CMD ["java","-Dspring.profiles.active=docker-demo", "-war", "webapp.war"]
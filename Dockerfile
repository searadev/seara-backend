FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/seara-backend-0.0.1-SNAPSHOT.jar seara-backend.jar
ENTRYPOINT [ "java", "-jar", "/seara-back.jar" ]
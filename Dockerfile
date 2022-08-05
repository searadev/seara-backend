FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/seara-back-0.0.1-SNAPSHOT.jar seara-back.jar
ENTRYPOINT [ "java", "-jar", "/seara-back.jar" ]
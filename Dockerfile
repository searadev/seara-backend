FROM openjdk:11

ARG ENV_DB_URL
ENV DB_URL=$ENV_DB_URL

ARG ENV_DB_USERNAME
ENV DB_USERNAME=$ENV_DB_USERNAME

ARG ENV_DB_PASSWORD
ENV DB_PASSWORD=$ENV_DB_PASSWORD

ENV APP_PROFILE hml

VOLUME /tmp
EXPOSE 8080
ADD ./target/seara-backend-0.0.1-SNAPSHOT.jar seara-backend.jar
ENTRYPOINT [ "java", "-jar", "/seara-backend.jar" ]
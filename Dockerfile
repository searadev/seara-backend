FROM openjdk:11

#ARG ENV_DB_URL=jdbc:postgresql://ec2-44-207-126-176.compute-1.amazonaws.com:5432/d7ifn7asd1qqt1
#ENV DB_URL=$ENV_DB_URL

#ARG ENV_DB_USERNAME=ubqwqdvtwfryzw
#ENV DB_USERNAME=$ENV_DB_USERNAME

#ARG ENV_DB_PASSWORD=27aaab37f2c3ca5ffe6337b4d8118045d8babb3177c46d8b37eb7e671d73afbd
#ENV DB_PASSWORD=$ENV_DB_PASSWORD

ENV APP_PROFILE hml

VOLUME /tmp
EXPOSE 8080
ADD ./target/seara-backend-0.0.1-SNAPSHOT.jar seara-backend.jar
ENTRYPOINT [ "java", "-jar", "/seara-backend.jar" ]
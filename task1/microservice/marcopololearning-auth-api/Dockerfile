FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="ilkinabdullayev6@gmail.com"
COPY build/libs/marcopolo-learning-auth-api-*.jar app.jar

## THE LIFE SAVER
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait


ENTRYPOINT ["/wait", "java","-jar","app.jar"]
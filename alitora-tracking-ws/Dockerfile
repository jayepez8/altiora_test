FROM openjdk:11.0.15

LABEL authors="Jonathan Yépez"

WORKDIR /app

COPY /build/libs/alitora-tracking-ws-*.jar /app/app.jar


EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
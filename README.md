# altiora_test

Technical/practical test for admission to altiora

## Requirements

This project was developed with:

- Java 11
- Gradle 6.8.2
- SpringBoot
- Node 18.19.1
- Angular
- Mysql
- Docker

## Build and deploy

To build the microservice alitora-tracking-ws you must use the command:

    gradle clean build

It is necessary to have the database running to successfully pass the integration tests, this can be done using docker and the command:

    docker-compose up -d

If you do not want to perform these tests, run:

    gradle clean build -x test

To build and deploy run these commands in a terminal. It is necessary to have docker running

    docker-compose build
    docker-compose up -d

It is not necessary to run the script in the database because it is already configured to run when the database is raised

## Ports

### Local

- alitora-tracking-ws (SpringBoot) -> 8081
- alitora-tracking-ng (Angular) -> 4200

### Container

- alitora-tracking-ws (SpringBoot) -> 8081
- alitora-tracking-ng (Angular) -> 80

# Wirecard Challenge

This guide walks you through the process of running the wirecard-challenge API backend. There are two ways for that:

## Running with Maven
###### What you'll need
- JDK 1.8 or later
- Apache Maven 3.3.3 or later
- MongoDB 4.0 or later

**Important:** For running with Maven, the wirecard-challenge application connect to mongo using "localhost" as URL. In this case, it possible running unit test before generating the build.

###### Steps for run:
- Download and unzip the source repository or clone it using Git: 
```
git clone https://github.com/sandersoncoelho/wirecard-challenge.git
```
- Enter into wirecard-challenge directory and build
```
mvn clean install
```
- Run:
```
mvn spring-boot:run
```
- Access [https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest](https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest) to get instructions to use Wirecard Challenge API.

## Running with Docker
###### What you'll need
- Docker 18.09.2 or later

**Important:** For running with Docker, the container generate from sandersoncoelho/wirecard-challenge image should connect to mongo from other container. Thus, we should create a common network to both containers. Furthermore, the sandersoncoelho/wirecard-challenge container expected an URL 10.11.0.2 for mongodb to work fine.

###### Steps for run:
- Create "wirecard-net" network
```
docker network create --subnet=10.11.0.0/16 --gateway=10.11.0.1 wirecard-net
```
- Download and start mongo container in wirecard-net. As first container in this network, mongo should get 10.11.0.2 as URL. Otherwise, check [Docker network documentation](https://docs.docker.com/network/) to start mongo with that specific URL.
```
docker run --network wirecard-net -d mongo
```
- Download and start sandersoncoelho/wirecard-challenge container:
```
docker run --network wirecard-net -p 8080:8080 sandersoncoelho/wirecard-challenge
```
- Access [https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest](https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest) for get instructions to use Wirecard Challenge API.

## The architecture and the design adopted

The API it was implemented using using Maven, Spring Boot, embedded Tomcat and MongoDB.

The API offers endpoints implemented with:
- Models: Entities modeling buyers, card, client and Payment;
- Enums: Constants used in application;
- Repositories: Classes witch implements crud operations in database, using models;
- Services: Where it is implemented the business of the API;
- Controllers: Interface exposing in fact the endpoints.
- Unit tests: Validating the endpoints.
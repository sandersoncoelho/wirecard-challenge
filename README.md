# Wirecard Challenge

This guide walks you through the process of running the wirecard-challenge API backend. There are two ways for that:

## Running with Maven
###### What you'll need
- JDK 1.8 or later
- Apache Maven 3.3.3 or later
- MongoDB 4.0 or later

###### Steps for run:
- Download and unzip the source repository or clone it using Git: 
```
git clone https://github.com/sandersoncoelho/wirecard-challenge.git
```
- Enter into wirecard-challenge
```
mvn spring-boot:run
```
- Access [https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest](https://documenter.getpostman.com/view/586792/S1Lzx793?version=latest) for get instructions to use Wirecard Challenge API.

## Running with Docker
###### What you'll need
- Docker 18.09.2 or later

###### Steps for run:
- Configure MongoDB in a container, such as wirecard-challenge container can access it.
- Run:
```
docker run -p 8080:8080 --network=host -t sandersoncoelho/wirecard-challenge
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
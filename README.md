# Factory DB Admin-Panel 

## General info
This project is an admin panel for secure interaction with the databases of a fictitious company - a furniture manufacturing factory.

## Used technologies
The project is written using JDK 21 and the Spring framework (Spring Boot, Spring Data, Spring MVC). It is a full-stack application that includes server-side and client-side components, as well as a PostgreSQL database server. The server provides a RESTful API built according to the MVC model. For database operations, the server uses Spring Data JPA, Hibernate, and JDBC. The backend includes Data Transfer Objects (DTOs) and mappers based on MapStruct. The server features data validation, defined exceptions, and their handler. API documentation is created using Swagger and Spring OpenAPI. The client application is written in JavaScript using the React framework and the Material-UI component library. PostgreSQL is used as the relational database server. A Dockerfile and docker-compose were used for containerization and deployment.

## Application structure
Application consists of next parts:

- **backend**: backend server application that provides RESTfull API for CRUD-operation with databases 

- **frontend**: frontend client application that provides GUI for interaction with necessary databases 

- **PostgreSQL server**: hosts SQL database for the needs of the factory

## Launching and usage
1. Clone this repository:
   ```git
   git clone https://github.com/danilalisichkin/factory-db-application
2. Change current directory to factory-db-application:
   ```sh
   cd factory-db-application
   ```
3. Launch docker-compose.yml:
   ```docker
   docker-compose up
   ```
4. Open any suitable browser. Next you can go to the next URLs:
    - http://localhost:8080/swagger-ui/index.html - to see documentation and test the server API
    - http://localhost:3000/ - to use client application and test GUI
   
   Note: defaults ports are:
    - port 8080 - Backend application
    - port 3000 - Frontend application
    - port 5432 - PostgreSQL server

    But you can change them in container settings using Docker
5. All done!
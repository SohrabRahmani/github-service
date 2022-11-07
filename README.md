# Software Engineering(Java) Home Task

## Description

Through the following endpoint and parameters, you can get the list of GitHub repositories with different filters:

- GET `/api/github/repo`
- Parameter:
    - created:  Using this parameter, can filter the date of repositories creation by accepting smaller and larger
      formats, such as all repos created after 2019, can call them with this format: ">2019-01-01"
    - language: Using this parameter, can filter the programing language of repositories
    - per_page: Using this parameter, can specify the number of repository per page to show
    - page:     Using this parameter, can specify the page number to show
    - sort:     Using this parameter, can specify sort the result by some parameter like star, created
    - order:    Using this parameter, can specify the sorting method (ASC, DESC)

## Document

- `Swagger` http://localhost:8080/swagger-ui/index.html

## Tools used

- IntelliJ IDEA 2022
- Postman

## Test Command

- `mvn clean test` - Plain maven clean and test

## Compilation Command

- `mvn clean install` - Plain maven clean and install

## Health Check Endpoint

- `Actuator` http://localhost:8080/actuator/health

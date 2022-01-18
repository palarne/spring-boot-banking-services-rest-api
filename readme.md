Simple application REST API using Spring Boot, JPA and H2.
REST API to perform some simple banking operations.
Run the application and open Postman to send requests.

@PUT example:
{
    "accountNumber": "03",
    "balance": 750.00 
}

@POST example:
{
    "accountNumberFrom": "01",
    "accountNumberTo": "02",
    "amount": 5.00
}

@GET example:
localhost:8080/account/balance/01

API documentation using Swagger, logging and validation is to be added at a later stage.

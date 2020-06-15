# Introduction 
Implement a simple contacts database to store personal and company contact details.
The requirement is to keep a list of customers and suppliers. A customer or supplier can be a person or a company, but not both. Additionally, a company or person can be a supplier and a customer at the same time.
For this implementation assume all fields are required, but no other validation is necessary.
Implement a service that provides the following data model and exposes a REST API. Security is not 

# Getting Started
Technology stack
Java11
Spring boot 2.2.2
H2 in memory database
junit

# Build and Test

mvn clean install 
mvn spring-boot:run -Dspring.profiles.active=local

# REST endpoint/Postman collection


------ Add Company-----

curl --location --request POST 'http://localhost:8080/g2tech/addcustomer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"SRR",
    "registration_number":"yete67657t",
    "areaCode":10,
    "number":"100",
    "last_order":"2020-03-30T00:00:00"
}'
------Add Person------
curl --location --request POST 'http://localhost:8080/g2tech/addcustomer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first_name":"Rony",
    "last_name":"D",
    "areaCode":10,
    "number":"100"
}'

------ Add Company-----

curl --location --request POST 'http://localhost:8080/g2tech/addsupplier' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name":"vineet",
    "registration_number":"yete67657t",
    "areaCode":10,
    "number":"100",
    "taxNumber":"2012-7854KKL",
    "Order_lead_time_in_days":45
}'

------Add Person------

curl --location --request POST 'http://localhost:8080/g2tech/addsupplier' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first_name":"vineet",
    "last_name":"jaiswal",
    "areaCode":10,
    "number":"100",
    "taxNumber":"2012-7854KKL",
    "Order_lead_time_in_days":45
}'
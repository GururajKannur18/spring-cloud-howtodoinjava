## Quick Guide to Microservices with Spring Boot 2.0, Eureka and Spring Cloud  [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

Detailed description can be found here: [Quick Guide to Microservices with Spring Boot 2.0, Eureka and Spring Cloud](https://piotrminkowski.wordpress.com/2018/04/26/quick-guide-to-microservices-with-spring-boot-2-0-eureka-and-spring-cloud/) 

https://piotrminkowski.wordpress.com/2018/04/26/quick-guide-to-microservices-with-spring-boot-2-0-eureka-and-spring-cloud/

# Sequence to start the microservices

1) config-service
2) discovery-service
3) microservices


# Eureka Server
http://localhost:8761/

# Swagger
http://localhost:8060/employee/v2/api-docs

# Department Service
http://localhost:8091/1

```
{"id":1,"organizationId":1,"name":"Development","employees":[{"id":1,"name":"John Smith","age":34,"position":"Analyst"},{"id":2,"name":"Darren Hamilton","age":37,"position":"Manager"},{"id":3,"name":"Tom Scott","age":26,"position":"Developer"}]}
```

http://localhost:8091/organization/1

```
[{"id":1,"organizationId":1,"name":"Development","employees":[{"id":1,"name":"John Smith","age":34,"position":"Analyst"},{"id":2,"name":"Darren Hamilton","age":37,"position":"Manager"},{"id":3,"name":"Tom Scott","age":26,"position":"Developer"}]},{"id":2,"organizationId":1,"name":"Operations","employees":[{"id":4,"name":"Anna London","age":39,"position":"Analyst"},{"id":5,"name":"Patrick Dempsey","age":27,"position":"Developer"}]}]
```


http://localhost:8060/organization/1/with-departments-and-employees

```
{
    "id": 1,
    "name": "Microsoft",
    "address": "Redmond, Washington, USA",
    "departments": [
        {
            "id": 1,
            "name": "Development",
            "employees": [
                {
                    "id": 1,
                    "name": "John Smith",
                    "age": 34,
                    "position": "Analyst"
                },
                {
                    "id": 2,
                    "name": "Darren Hamilton",
                    "age": 37,
                    "position": "Manager"
                },
                {
                    "id": 3,
                    "name": "Tom Scott",
                    "age": 26,
                    "position": "Developer"
                }
            ]
        },
        {
            "id": 2,
            "name": "Operations",
            "employees": [
                {
                    "id": 4,
                    "name": "Anna London",
                    "age": 39,
                    "position": "Analyst"
                },
                {
                    "id": 5,
                    "name": "Patrick Dempsey",
                    "age": 27,
                    "position": "Developer"
                }
            ]
        }
    ],
    "employees": []
}
``` 
 
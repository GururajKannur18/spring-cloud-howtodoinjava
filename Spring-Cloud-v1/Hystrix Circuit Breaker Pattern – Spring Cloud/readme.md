# Hystrix Circuit Breaker Pattern – Spring Cloud

Learn to leverage the one of the Spring cloud Netflix stack component called Hystrix to implement circuit breaker while invoking underlying microservice. It is generally required to enable fault tolerance in the application where some underlying service is down/throwing error permanently, we need to fall back to different path of program execution automatically. This is related to distributed computing style of Eco system using lots of underlying Microservices. This is where circuit breaker pattern helps and Hystrix is an tool to build this circuit breaker.

# Whay is Circuit Breaker Pattern?
If we design our systems on microservice based architecture, we will generally develop many Microservices and those will interact with each other heavily in achieving certain business goals. Now, all of us can assume that this will give expected result if all the services are up and running and response time of each service is satisfactory.

Now what will happen if any service, of the current Eco system, has some issue and stopped servicing the requests. It will result in timeouts/exception and the whole Eco system will get unstable due to this single point of failure.

Here circuit breaker pattern comes handy and it redirects traffic to a fall back path once it sees any such scenario. Also it monitors the defective service closely and restore the traffic once the service came back to normalcy.

So circuit breaker is a kind of a wrapper of the method which is doing the service call and it monitors the service health and once it gets some issue, the circuit breaker trips and all further calls goto the circuit breaker fall back and finally restores automatically once the service came back !! That’s cool right?

# Hystrix Circuit Breaker Example
To demo circuit breaker, we will create following two microservices where first is dependent on another.

- Student Microservice – Which will give some basic functionality on Student entity. It will be a REST based service. We will call this service from School Service to understand Circuit Breaker. It will run on port 8098 in localhost.
- School Microservice – Again a simple REST based microservice where we will implement circuit breaker using Hystrix. Student Service will be invoked from here and we will test the fall back path once student service will be unavailable. It will run on port 9098 in localhost.

# Tech Stack and Demo Runtime
- Java 1.8
- Eclipse as IDE
- Maven as build tool
- Spring cloud Hystrix as circuit breaker framework
- Spring boot
- Spring Rest

Build and Test Student Service
Now do a final build using mvn clean install and run the server using command java -jar target\spring-hystrix-student-service-0.0.1-SNAPSHOT.jar. This will start the student service in default port 8098.

Open browser and type ``http://localhost:8098/getStudentDetailsForSchool/abcschool``.

```
[
    {
        "name": "Sajal",
        "className": "Class IV"
    },
    {
        "name": "Lokesh",
        "className": "Class V"
    }
]
```

# Create School Service – Hystrix Enabled
Similar to Student service, create another microservice for School. It will internally invoke already developed Student Service.

# Generate spring boot project
Create a Spring boot project from Spring Boot initializer portal with those dependencies mainly.

- Web – REST Endpoints
- Actuator – providing basic management URL
- Hystrix – Enable Circuit Breaker
- Hystrix Dashboard – Enable one Dashboard screen related to the Circuit Breaker monitoring

# Build and Test of School Service
Now do a final build using mvn clean install and run the server using command java -jar target\spring-hystrix-school-service-0.0.1-SNAPSHOT.jar. This will start the school service in default port 9098.

Start the student service as described above and then test school service by opening browser and type ``http://localhost:9098/getSchoolDetails/abcschool``.

```
NORMAL FLOW !!! - School Name - abcschool ::: Student Details [{"name":"Sajal","className":"Class IV"},{"name":"Lokesh","className":"Class V"}] - Sat Jan 19 19:28:39 IST 2019
```

# Test Hystrix Circuit Breaker – Demo

Off the ``spring-hystrix-student-service`` and then execute below 

Opening browser and type ``http://localhost:9098/getSchoolDetails/abcschool``.


```
CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - Sat Jan 19 19:30:31 IST 2019
```


# Hystrix Dashboard
As we have added hystrix dashboard dependency, hystrix has provided one nice Dashboard and a Hystrix Stream in the bellow URLS:

``http://localhost:9098/actuator/hystrix.stream`` – It’s a continuous stream that Hystrix generates. 

- http://localhost:9098/hystrix – This is visual dashboard initial state.

Now add ``http://localhost:9098/hystrix.stream`` in dashboard to get a meaningful dynamic visual representation of the circuit being monitored by the Hystrix component.

# Summary
That’s all about creating spring could Hystrix Circuit Breaker, we have tested both circuit open path and circuit closed path. Do the setup on your own and play with different combination service state to be more clear of whole concept.


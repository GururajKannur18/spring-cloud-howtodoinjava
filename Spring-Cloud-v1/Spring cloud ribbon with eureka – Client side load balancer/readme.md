# Spring cloud ribbon with eureka – Client side load balancer example

In this Spring cloud tutorial, learn to use client side load balancing using Netflix Ribbon in spring boot/cloud projects. Learn to build microservice based applications which use ribbon as client side load balancer and eureka as registry service. Learn how we can dynamically add new instances of microservices under the load balancer.

# 1. Traditional server side load balancing
Server side load balancing is involved in monolithic applications where we have limited number of application instances behind the load load balancer. We deploy our war/ear files into multiple server instances which are basically a pool of server having the same application deployed and we put a load balancer in front of it.

Load balancer has a public IP and DNS. The client makes a request using that public IP/DNS. Load balancer decides to which internal application server request will be forwarded to. It mainly use round robin or sticky session algorithm. We call it server side load balancing.

# 1.1. Problems in microservices architecture
Mostly server side load balancing is a manual effort and we need to add/remove instances manually to the load balancer to work. So ideally we are loosing the today’s on demand scalability to auto-discover and configure when any new instances will be spinned of.

Another problem is to have a fail-over policy to provide the client a seamless experience. Finally we need a separate server to host the load balancer instance which has the impact on cost and maintenance.


# 2. Client side load balancing
To overcome the problems of traditional load balancing, client side load balancing came into picture. They reside in the application as inbuilt component and bundled along with the application, so we don’t have to deploy them in separate servers.

Now let’s visualize the big picture. In microservice architecture, we will have to develop many microservices and each microservice may have multiple instances in the ecosystem. To overcome this complexity we have already one popular solution to use service discovery pattern. In spring boot applications, we have couple of options in the service discovery space such as eureka, consoul, zookeeper etc.

Now if one microservice wants to communicate with another microservice, it generally looks up the service registry using discovery client and Eureka server returns all the instances of that target microservice to the caller service. Then it is the responsibility of the caller service to choose which instance to send request.

Here the client side load balancing comes into picture and automatically handles the complexities around this situation and delegates to proper instance in load balanced fashion. Note that we can specify the load balancing algorithm to use.


# 3. Netflix ribbon – Client side load balancer
Netflix ribbon from Spring Cloud family provides such facility to set up client side load balancing along with the service registry component. Spring boot has very nice way of configuring ribbon client side load balancer with minimal effort. It provides the following features

- Load balancing
- Fault tolerance
- Multiple protocol (HTTP, TCP, UDP) support in an asynchronous and reactive model
- Caching and batching

#4. Netflix ribbon example

#4.1. Technology stack
- Java, Eclipse, Maven as Development Environment
- Spring-boot and Cloud as application framework
- Eureka as Service registry Server
- Ribbon as Client Side Load balancer

We will create the following components and see how the whole eco system coordinates in distributed environment.

- Two microservices using Spring boot. One needs to invoke another as per business requirement
- Eureka service registry server
- Ribbon in the invoking microservice to call the other service in load balanced fashion WITH service discovery
- Invoking service in load balanced manner WITHOUT service discovery

# Spring Eureka

URL: http://localhost:8761/

# ribbon-server

- http://localhost:9090/
- http://localhost:9090/backend

```
Hello form Backend!!! Host : localhost :: Port : 9090
```

# ribbon-client
http://localhost:8888/client/frontend

```
Server Response :: Hello form Backend!!! Host : localhost :: Port : 9090
```

# 5. Test the application
# 5.1. Start components
Do the final build use command mvn clean install and check if the build is successful. If there is any error you need to fix those to proceed. Once we have successful build for all the maven projects, we will start the services one by one.

Eureka first, then the back-end micro service and finally the frontend micro service.

To start each microservice, we will use 'java -jar -Dserver.port=XXXX target/YYYYY.jar' command.

# 5.2. Deploy multiple instances of backend microservice
To do that we need to use different port for this, to start service in a specific port we need to pass the port in this way.
java -jar -Dserver.port=XXXX target/YYYYY.jar. We will create 3 instances of this service in ports 9090, 9091 and 9092 ports.

# 5.3. Verify eureka server
Now go to http://localhost:8761/ in browser and check that eureka server is running with all microservices are registed with desired number of instances.

# 5.4. Check if client side load balancing is working
In the frontend microservice, we are calling the backend microservice using RestTemplate. Rest tempate is enabled as client side load balancer using @LoadBalanced annotation.

Now go to browser and open the client microservice rest endpoint http://localhost:8888/client/frontend and see that response is coming from any one of the backend instance.

To understand this backend server is returning it’s running port and we are displaying that in client microservice response as well. Try refreshing this url couple of times and notice that the port of backend server keeps changing, that means client side load balancing is working. Now try to add more instance of backend server and check that is also registered in eureka server and eventually considered in ribbon, as once that will be registered in eureka and ribbon automatically ribbon will send request to the new instances as well.

# 5.5. Test with hard code backends without service discovery
Go the frontend microservice application.properties file and enable this.

```
application.properties
server.ribbon.listOfServers=localhost:9090,localhost:9091,localhost:9092
server.ribbon.eureka.enabled=false
```

Now test the client url. You will get response from the registered instances only. Now if you start new instance of backend microservice in different port, Ribbon will not send request to the new instance until we register that manually in the ribbon.

If you have difficulty in testing this, I will suggest too remove all the eureka related configurations from all the applications and also stop the eureka server. Hope you will not face any difficulty in testing this as well.


# 6. Summary
So we have seen how easily we can use Ribbon along with Eureka in spring boot microservice development.


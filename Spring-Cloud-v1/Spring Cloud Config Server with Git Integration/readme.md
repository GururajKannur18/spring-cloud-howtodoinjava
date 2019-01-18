# Spring Cloud Config Server with Git Integration

Now-a-days we often come across the term Microservices while discussing any new java based server side development (mainly service API). Microservices approach now has become an industry standard for any new API development and almost all the organizations are promoting it.

In this tutorial, we will discuss and do a demo on a specific Microservice feature called Config Server. Config server is where all configurable parameters of microservices are written and maintained. It is more like externalizing properties / resource file out of project codebase to an external service altogether, so that any changes to that property does not necessitate the deployment of service which is using the property. All such property changes will be reflected without redeploying the microservice.

#Why to Use Spring Cloud Config Server
The idea of config server has come from the 12-factor app manifesto related to best practice guideline of developing modern cloud native application. It suggests to keep properties / resources in the environment of the server where the values of those resources vary during run time – usually different configurations that will differ in each environment.

As an example, let’s say one service is dependent on another service (invoked for certain business scenario) and if that dependent service URL got changed to some other service, and then usually we need to build and deploy our service with the updated location is required. Now if we go by 12 factor app approach and if we read those config from external service deployed as different process, then we just need to refresh that config server.

So, the idea is very clear and effective. Let’s now see how we can create config server.


#Tech Stack Used in Example
We will be using spring-boot based spring-cloud API that is easily available and very popular. It is called Config Server in spring framework nomenclature. Also, we will use git configuration to host the properties file.

So finally, our technology stack for this demo will be:

- Java 1.8
- Eclipse IDE
- Spring cloud
- Spring boot
- Spring Rest
- GitHub as resource repository
- Maven
- REST client

To start with we will develop two Microservices using spring boot.
1. one is the config server service, providing the configuration in runtime
2. one is the config client service, using the configuration exposed as config server.

#Create the Git repository
Next very important step is to create a local git repository. It can easily be converted to a remote repository later by configuring it’s URL in the properties file. We will place the external property file [configuration], which will be used by the Config server Microservice to provide the external configuration of properties. We need to follow the below steps to create a local git repository and check in sample properties files.

1. Make sure you have git shell installed in your machine and you can run git bash from command prompt. To verify it open command prompt and type git, if it recognize the command then you probably have the git prompt installed, if not please follow git website, download and install as per the instruction.
2. Now Create a directory config-server-repo in your Desktop.
3. Then create a file config-server-client.properties file in the config-server-repo directory and add the message there msg = Hello world - this is from config server.
4. Then create another file config-server-client-development.properties file in the config-server-repo directory and add the message there msg = Hello world - this is from config server – Development environment.
5. Then create another file config-server-client-production.properties file in the config-server-repo directory and add the message there msg = Hello world - this is from config server – Production environment.
6. Here we are maintaining same property name for different environment, as we generally maintain properties for different environments like urls, credentials, database details etc. Here the most important point is that we need to append hyphen (-) with the environment name in each property so that config server understands it. Also, we need to name the properties file with the config client service name that we will create after this.
7. Now open command prompt from config-server-repo directory and run command git init to make that directory as git repository.
8. Now run git add . to add everything to this repo.
9. Then finally we need to commit the properties file by running command git commit –m "initial checkin". This should check in all the files in the git repository.

```
pc@DESKTOP-NQ639DU MINGW64 ~/Desktop/config-server-repo
$ git init
Initialized empty Git repository in C:/Users/pc/Desktop/config-server-repo/.git/

pc@DESKTOP-NQ639DU MINGW64 ~/Desktop/config-server-repo (master)
$ git add .

pc@DESKTOP-NQ639DU MINGW64 ~/Desktop/config-server-repo (master)
$ git commit -am "Initial commit"
[master (root-commit) 96ac3d4] Initial commit
 3 files changed, 3 insertions(+)
 create mode 100644 config-server-client-development.properties
 create mode 100644 config-server-client-production.properties
 create mode 100644 config-server-client.properties

pc@DESKTOP-NQ639DU MINGW64 ~/Desktop/config-server-repo (master)

```

Now open browser and check below Urls, it will return the JSON output and in propertySources section we can see all the properties we have added in the properties. This ensures that config-server is running successfully, it has recognized the git location and it is serving configuration for different environments.

- http://localhost:8888/config-server-client/development
- http://localhost:8888/config-server-client/production

Also, check if any runtime change in the property file is reflected by the server without restart – Do any change in the value of any environment’s property and check-in that file and then run that specific environment’s endpoint, and verify that changed value should be reflected immediately without restarting the server – that is the magic of Spring Config Server.

To do the git check in, after doing the change and save the file by any text editor, run the command git add . and git commit -m "test" from config-server-repo directory in the desktop.


#Rest Endpoints

http://localhost:8080/msg

```
Hello world - this is from config server
```

#Test Property Change
Now we will do a property change and test if this can be reflected in the config client service without restarting any of the Microservices.
Do some change, in the value of the msg property in the config-server-client-development.properties and check-in in the local git, then hit the http://localhost:8080/msg again in the browser, You will the old value only.

To reflect the new value, we need to refresh the configuration by hitting http://localhost:8080/refresh endpoint using POST method from any of the REST client.

Once you have successfully refreshed the config client service, the new value should be reflected in the service response. This is because @RefreshScope annotation the Rest Controller that we have exposed.


#Things to check if facing any error
Property file name and the Client module service name spring.application.name=config-server-client should be exactly same, otherwise, properties will not be detected. Actually, Config Server exposes the properties in an end point of property file name, if you browse URL http://localhost:8888/config-server-client/development it will return all the dev environment values.

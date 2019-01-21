Shut down all the services, follow the below steps and then start the services:
 
-       Create a configuration directory (say c:\configuration)
-       Copy the configuration files (*.yml files from the zip) into this folder (c:\configuration)
-       Open a git-bash shell and in the terminal, navigate to the folder i.e. cd /d c:\configuration. Run the below commands
			o git init .
			o git add .
			o git commit -a -m "latest"

-       Now, switch back to the config-server code and run the config server
-       Then run the "discovery service" and during the startup, you should see a message stating that it is connecting to config-server and should locate the configuration for discovery server
-       Then start the "edge service"
-       Also, check the discovery-service.yml and see if the server.port is set to 8761. If not, set it to 8761 and start

Note: Make sure to create the msa db in mysql
========================================
#Create Token

```
curl -XPOST --user "meetup:secret" -d "grant_type=password&username=cv@yopmail.com&password=" http://localhost:9191/oauth/token
```

To Beat the MySQL Error Code 1175 - Use ``SET SQL_SAFE_UPDATES = 0;``

#Create Sample Data

```
INSERT INTO `meetup`.`product` (`id`, `description`, `name`) VALUES ('1', 'Product Description', 'Product Name');
INSERT INTO `meetup`.`product_rating` (`id`, `comments`, `product_id`, `rating`) VALUES ('2', 'Lenovo 1', '1', '11');
```

Example: 
# Get all Products:
```
curl -XGET http://localhost:8080/api/products
```

# Get details about a specific product (say 1)

```
curl -XGET http://localhost:8080/api/products/1
```

Headers can be specified via the -H
 
i.e.
curl -XGET -H "Content-Type: application/json" http://localhost:8080/api/products/1
 
Multiple headers can be passed using multiple �H
curl �XGET �H �Content-Type: application/json� �H �Authorization: Bearer <accesstoken>� http://localhost:8080/api/products/1
 
For Post (create a new product), the payload can be specified using –d
 
curl –XPOST –H "Content-Type: application/json" –d '{"name": "Product Name", "description": "Product Description"}' http://localhost:8080/api/products



~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
curl -XPOST --user "meetup:secret" -d "grant_type=password&username=cv@yopmail.com&password=" http://localhost:9191/oauth/token  ==> WORKING

curl -XGET -H "Authorization: Bearer 604a1951-007f-4ba0-b149-2c0ca854bea9" http://localhost:8080/api/products/1

{"id":1,"name":"Lenovo 1","description":"Lenovo 1","rating":11.0}

curl -XGET -H "Authorization: Bearer 32413787-7fcb-4381-b11a-0ddb1f6231f2" http://localhost:8080/api/ratings/products/1
11.0

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

curl -XPOST --user 'meetup:secret' -d 'grant_type=password&username=<whatever-is-the-username>&password=<whatever-is-the-user-password>' http://localhost:9191/oauth/token

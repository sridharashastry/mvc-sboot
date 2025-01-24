

#Download docker image of mysql


	PS C:\ws> docker pull mysql:latest
	latest: Pulling from library/mysql
	2c0a233485c3: Pull complete
	21577e00f2ba: Pull complete
	c294da35c13e: Pull complete
	facc8f3107c1: Pull complete
	de4342aa4ad8: Pull complete
	4643f1cf56c2: Pull complete
	139aca660b47: Pull complete
	b10e1082570e: Pull complete
	26313a3e0799: Pull complete
	d43055c38217: Pull complete
	Digest: sha256:45f5ae20cfe1d6e6c43684dfffef17db1e1e8dc9bf7133ceaafb25c16b10f31b
	Status: Downloaded newer image for mysql:latest
	docker.io/library/mysql:latest

	Whats Next?
	  View a summary of image vulnerabilities and recommendations → docker scout quickview mysql:latest
	PS C:\ws>



#Run the container with port mappings

	PS C:\ws>  docker run -d -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=root mysql:latest
	be206042494d512031645cd779969e3d3b6e24d3af0af223d97700006298906a

	#Check the containers
	PS C:\ws> docker ps
	CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                               NAMES
	be206042494d   mysql:latest   "docker-entrypoint.s…"   6 seconds ago   Up 5 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql-server
	12e8e29b6bc7   mongo:6.0      "docker-entrypoint.s…"   8 hours ago     Up 8 hours     0.0.0.0:27017->27017/tcp            mongodb

	#Check the port mappings

	PS C:\ws>  docker inspect mysql-server | findstr "3306"
					"3306/tcp": [
							"HostPort": "3306"
					"3306/tcp": {},
					"33060/tcp": {}
					"3306/tcp": [
							"HostPort": "3306"
					"33060/tcp": null
	PS C:\ws>




#############Below is not required, for testing only we can do this as it does not have port explicitely mentioned

#Run the container without mentioning any port : This will not work when Spring boot apps try to connect


	PS C:\ws>  docker run --name mysql-server -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
	609d2bb9b8faa1b51b808f48760259d2c4508d350889a9ca15fc7c0d45eab2d5
	PS C:\ws>

	#Notice the port mapping

	PS C:\ws>  docker inspect mysql-server | findstr "3306"
					"3306/tcp": {},
					"33060/tcp": {}
					"3306/tcp": null,
					"33060/tcp": null
	PS C:\ws>


############################NOT REQUIRED ####################################


#Enter into the container

	PS C:\ws>  docker exec -it mysql-server mysql -u root -p
	Enter password:
	Welcome to the MySQL monitor.  Commands end with ; or \g.
	Your MySQL connection id is 11
	Server version: 9.2.0 MySQL Community Server - GPL

	Copyright (c) 2000, 2025, Oracle and/or its affiliates.

	Oracle is a registered trademark of Oracle Corporation and/or its
	affiliates. Other names may be trademarks of their respective
	owners.

	Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.


#Show the databases

	mysql> SHOW DATABASES;
	+--------------------+
	| Database           |
	+--------------------+
	| information_schema |
	| mysql              |
	| performance_schema |
	| sys                |
	+--------------------+
	4 rows in set (0.01 sec)

	mysql>









############################################################All the below invocations are done 'AFTER SPRING BOOT APPLICATION IS CREATED'

#################REFER LAB034-BUNDLED, that contains two microservices viz., Order-Service and Product-Service

#The spring boot application requires a table by name 'order_service' DATABASE to be available
#Hence below are the commands to create a DATABASE


#Create a database

	mysql> CREATE DATABASE order_service;
	Query OK, 1 row affected (0.00 sec)


#Show the databases


	mysql> show databases
		-> ;
	+--------------------+
	| Database           |
	+--------------------+
	| information_schema |
	| mysql              |
	| order_service      |
	| performance_schema |
	| sys                |
	+--------------------+
	5 rows in set (0.00 sec)


#Use / Switch the database

	mysql> USE order_service;
	Reading table information for completion of table and column names
	You can turn off this feature to get a quicker startup with -A

	Database changed

#Show the tables. Notice the tables are auto generated/created


	mysql> SHOW TABLES;
	+------------------------------+
	| Tables_in_order_service      |
	+------------------------------+
	| t_order                      |
	| t_order_items                |
	| t_order_items_seq            |
	| t_order_order_line_item_list |
	| t_order_seq                  |
	+------------------------------+
	5 rows in set (0.00 sec)

	mysql>



#Have the Springboot application running

#Once the curl is fired with some data as below, we can see the tables getting updated.

curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"SKU123","price":100.5,"quantity":2},{"skuCode":"SKU124","price":200.75,"quantity":1}]}'

curl -X POST http://localhost:8081/api/order -H "Content-Type: application/json" -d '{"orderLineItemDtoList":[{"skuCode":"SKU125","price":300.5,"quantity":21},{"skuCode":"SKU126","price":400.75,"quantity":11}]}'




		mysql> select * from t_order;
		+----+--------------------------------------+
		| id | order_number                         |
		+----+--------------------------------------+
		|  1 | b31b3cca-38e1-482e-9b7c-6116a69deaba |
		+----+--------------------------------------+
		1 row in set (0.00 sec)

		mysql> select * from t_order_items;
		+----+--------+----------+----------+
		| id | price  | quantity | sku_code |
		+----+--------+----------+----------+
		|  1 | 100.50 |        2 | SKU123   |
		|  2 | 200.75 |        1 | SKU124   |
		+----+--------+----------+----------+
		2 rows in set (0.00 sec)


		mysql> select * from t_order_order_line_item_list;
		+----------+-------------------------+
		| order_id | order_line_item_list_id |
		+----------+-------------------------+
		|        1 |                       1 |
		|        1 |                       2 |
		+----------+-------------------------+
		2 rows in set (0.00 sec)

		mysql>
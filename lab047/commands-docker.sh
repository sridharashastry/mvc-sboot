
##SPRING BOOT HELLO WORLD - DOCKER - AKS START

		#Download Spring Boot Project

			curl https://start.spring.io/starter.zip -d dependencies=web -d type=maven-project -d groupId=com.bring -d artifactId=hworld -d name=hworld -d packageName=com.bring.hworld -d javaVersion=17 -o hworld.zip

		#Unzip the zip. Below is Powershell command

			Expand-Archive -Path hworld.zip -DestinationPath hworld

		#Navigate to the extracted folder and create a java file. Below is example director


			cd C:\ws\sboot\lab046\src\main\java\com\bring\hworld


		#Ensure Java file is added. Check other cheat sheet

		#Below is powershell command to create the file. Copy from @ symbol to @symbol and paste in above directore.

		@"
			package com.bring.hworld;

			import org.springframework.web.bind.annotation.GetMapping;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RestController;

			@RestController
			@RequestMapping("/api")
			public class HelloWorldController {

				@GetMapping("/hello")
				public String sayHello() {
					return "hello world";
				}
			}
		"@ | Out-File -FilePath "HelloWorldController.java" -Encoding utf8

		#This is the file, if not powershell and want to create from IDE

			package com.bring.hworld;

			import org.springframework.web.bind.annotation.GetMapping;
			import org.springframework.web.bind.annotation.RequestMapping;
			import org.springframework.web.bind.annotation.RestController;

			@RestController
			@RequestMapping("/api")
			public class HelloWorldController {

				@GetMapping("/hello")
				public String sayHello() {

          System.out.println("Saying Hello to world ... ");
					return "hello world";
				}
			}

		#Navigate to pom.xml directory. Eg. as below.

		cd C:\ws\sboot\lab046

		dir

		PS C:\ws\sboot\lab046> dir

			Directory: C:\ws\sboot\lab046

		Mode                 LastWriteTime         Length Name
		----                 -------------         ------ ----
		d----            2/4/2025  4:26 PM                .mvn
		d----            2/4/2025  4:26 PM                src
		-a---            2/4/2025  4:26 PM             38 .gitattributes
		-a---            2/4/2025  4:26 PM            395 .gitignore
		-a---            2/4/2025  4:26 PM           1243 HELP.md
		-a---            2/4/2025  4:26 PM          10665 mvnw
		-a---            2/4/2025  4:26 PM           6912 mvnw.cmd
		-a---            2/4/2025  4:28 PM           1399 pom.xml

		PS C:\ws\sboot\lab046>




		#build the project

			mvn clean package

		#Run the jar locally and test



		PS C:\ws\sboot\lab046> java -jar .\target\hworld-docker-0.0.1-SNAPSHOT.jar

		........
		2025-02-04T16:33:37.604Z  INFO 10292 --- [hworld] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
		2025-02-04T16:33:37.621Z  INFO 10292 --- [hworld] [           main] com.bring.hworld.HworldApplication       : Started HworldApplication in 1.958 seconds (process running for 2.429)



		#Invoke the curl


		PowerShell 7.6.0-preview.2
		PS C:\ws> curl -X GET http://localhost:8080/api/hello
		hello world
		PS C:\ws>



		#STOP the java app before proceeding to next step

		#ADD/CREATE  Dockerfile in parallel to pom.xml. Below is simple powershell command to create docker file


		@"
		# Use an official OpenJDK 17 runtime as a parent image
		FROM openjdk:17-jdk-slim

		# Set the working directory in the container
		WORKDIR /app

		# Copy the packaged jar file into the container and rename it to app.jar
		COPY target/*.jar app.jar

		# Run the jar file
		ENTRYPOINT ["java", "-jar", "app.jar"]
		"@ | Out-File -FilePath "Dockerfile" -Encoding utf8


	#Post creation of the file

			Directory: C:\ws\sboot\lab046

		Mode                 LastWriteTime         Length Name
		----                 -------------         ------ ----
		d----            2/4/2025  4:26 PM                .mvn
		d----            2/4/2025  4:26 PM                src
		d----            2/4/2025  4:33 PM                target
		-a---            2/4/2025  4:26 PM             38 .gitattributes
		-a---            2/4/2025  4:26 PM            395 .gitignore
		-a---            2/4/2025  4:42 PM            299 Dockerfile
		-a---            2/4/2025  4:26 PM           1243 HELP.md
		-a---            2/4/2025  4:26 PM          10665 mvnw
		-a---            2/4/2025  4:26 PM           6912 mvnw.cmd
		-a---            2/4/2025  4:28 PM           1399 pom.xml

		PS C:\ws\sboot\lab046>



		#Ensure docker is also up and running. Below is powershell command

		PS C:\ws\sboot\lab046> docker ps > $null 2>&1; if ($?) { "Docker is running" } else { "Docker is not running" }
		Docker is running
		PS C:\ws\sboot\lab046>


		#Build, run, list the docker image

			docker build -t hworld:latest .

PS C:\ws\sboot\lab047> docker build -t hworld:latest .
[+] Building 0.7s (8/8) FINISHED                                                                                            docker:default
 => [internal] load build definition from Dockerfile                                                                                  0.0s
 => => transferring dockerfile: 149B                                                                                                  0.0s
 => [internal] load metadata for docker.io/library/eclipse-temurin:17-jre-alpine                                                      0.5s
 => [internal] load .dockerignore                                                                                                     0.0s
 => => transferring context: 2B                                                                                                       0.0s
 => [1/3] FROM docker.io/library/eclipse-temurin:17-jre-alpine@sha256:0be87fee3f75738a8b9e457404cab39ec2ede8ad08d61e1345f406a029596f  0.0s
 => [internal] load build context                                                                                                     0.0s
 => => transferring context: 101B                                                                                                     0.0s
 => CACHED [2/3] WORKDIR /app                                                                                                         0.0s
 => CACHED [3/3] COPY target/*.jar app.jar                                                                                            0.0s
 => exporting to image                                                                                                                0.0s
 => => exporting layers                                                                                                               0.0s
 => => writing image sha256:5211c8727e738ca2b1dac28321c68a7098e684e23e6ae0871b911e4e77532778                                          0.0s
 => => naming to docker.io/library/hworld:latest                                                                                      0.0s

View build details: docker-desktop://dashboard/build/default/default/jv5wtgysmh4od6ezq505g3kgy

  View a summary of image vulnerabilities and recommendations → docker scout quickview
PS C:\ws\sboot\lab047> docker images
REPOSITORY                  TAG       IMAGE ID       CREATED              SIZE
hworld                      latest    5211c8727e73   About a minute ago   201MB

PS C:\ws\sboot\lab047>

			docker run -p 8080:8080 hworld:latest

			#in another terminal
			docker container list

			http://localhost:8080/api/hello

		#Stop the application



##################################FROM THIS POINT NOT TESTED WHILE DOING THE LABS ####################NEED A PROPER AZURE ENVIRONMENT
##############HOWEVER, BELOW COMANDS WORK AS LONG AS THE NEEDED ACCESS AND ENVIRONMENT IS AVAILABLE #################


		#Important : Docker Demon should be up and running before proceeding to next step.
		#Also a proper azure account must exist and logged in.
		#List Azure Container Registry associated with a given RG on Azure

			az acr list --resource-group ZENPAY-DEV-RG-NG --output table

		# Login to the ACR. MUST. Ensure you login from beginning.

			az acr login --name ZenpayDevNGImages

		#Tag, push the local docker image to AZURE CONTAINER REGISTRY  (ACR)
			docker images
			docker tag hworld:latest zenpaydevngimages.azurecr.io/hworld:latest
			docker images
			docker push zenpaydevngimages.azurecr.io/hworld:latest
			az acr repository list --name ZenpayDevNGImages --output table
			#or with a filter
			az acr repository list --name ZenpayDevNGImages --output table | Select-String 'hworld'


#Ensure Deployment.yaml is added as below in parallel to Dockerfile

			apiVersion: apps/v1
    	kind: Deployment
    	metadata:
    	  name: hworld-deployment
    	spec:
    	  replicas: 1
    	  selector:
    		matchLabels:
    		  app: hworld
    	  template:
    		metadata:
    		  labels:
    			app: hworld
    		spec:
    		  containers:
    		  - name: hworld
    			image: zenpaydevngimages.azurecr.io/hworld:latest
    			ports:
    			- containerPort: 8080
    	---
    	apiVersion: v1
    	kind: Service
    	metadata:
    	  name: hworld-service
    	spec:
    	  type: LoadBalancer
    	  ports:
    	  - port: 80
    		targetPort: 8080
    	  selector:
    		app: hworld




	#Switch to correct namespace and apply the deployment

			kubectl config set-context --current --namespace="intg-poc"
			kubectl create -f .\Deployment.yaml
			kubectl get all
		#Check in the browser using the ip address provided by AKS

			http://57.153.160.96:80

		#Delete the deployment
			kubectl delete -f .\Deployment.yaml

		#Delete the ACR image
			az acr repository delete --name ZenpayDevNGImages --image hworld:latest

##Spring Boot - Docker - AKS   END

===============================================






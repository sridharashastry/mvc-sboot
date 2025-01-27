Points to consider / understand for this project.

1. We are adding a Eureka Server.
2. For that a new maven module is added by name 'discovery-service'
3. In order to download the correct maven packages, added below items in parent pom.xml

Refer Screenshot

 4. In the discovery-service pom.xml added below

Refer Screenshot


5. Added 'eureka-client' in all the three microservices. Check pom.xml for below tag

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>


6. Starting sequence

    first discover-service
    followed by others


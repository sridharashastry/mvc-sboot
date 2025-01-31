1. Integrating Keycloak with API GW.
2. Check the word document that contains steps to create the realms and clients.
3.In the API GW pom, added two security related dependencies viz., spring-boot-starter-oauth2-resource-server & spring-boot-starter-security
4. Read the curl invocations.sh file for obtaining the token etc.
5. Adding Spring boot security to Eureka Server as well in order to be accessed via API GW but securly. Note that the other microservices are accessible via gateway, however, being subjected to security via keycloak.
6. When it comes to Eureka Server (the UI), it is also subjected, however, we don't pass any token to it.
7. In order to do that, we need to add same type of security dependencies in pom.xml and add some additional configuration.

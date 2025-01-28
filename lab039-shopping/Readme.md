1. In this project, eureka is already added.
2. The configuration in Inventory_service is changed in properties file to start the service on random port. Check properties file.
3. This way wa can open more than one terminals and run the command to start the jars. This will spin up multiple applications, all running in different ports and getting registered on eureka.
4. Important configuration on order_service is as below.
    a. usage of WebClient.Builder in the WebClientConfig
    b. usage of  @LoadBalanced in the WebClientConfig
    c. usage of 'service-name' instead of localhost and port.

5. Always start the eureka server first, then followed by child services.
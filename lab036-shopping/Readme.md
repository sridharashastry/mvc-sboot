Points to consider / understand for this project.

1. In this project, interservice communication is being implemented.
2. This is achieved using the 'WebClient' approach.
3. There are three possible ways to connect or implement inter-service communications 'RestTemplate',  'WebClient' and 'FeignClient'
4. RestTemplate is being discontinued in later version of spring boot.

Changes in this project

1. The modules are renamed with suffix '2', becuase similar modules exist in previous lab and to avoid conflict it is renamed such.
2. Introduction of WebClient
3. In Pom.xml of 'order-service' addition of below

           <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-webflux</artifactId>
            </dependency>
4. Added 'WebClientConfig' configuration.
4. Added a DTO in Inventory-Service microservice/project
6. Added a new method called validateStockUsingSkuCodeList() in Service
7. Added a new abstract implementation  List<Inventory> findBySkuCodeIn(List<String> skuCode);
8. Copied this DTO from Inventory-Service to Order-Service so as to use the same data fields.
9. We need to create two databases before spring boot application is started viz., order_service and inventory_service


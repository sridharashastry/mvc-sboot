1. Circuit Breaker Implementation diagram added.
2. Added necessary dependency (resilience4j & actuator) in pom.xml of 'order-service'
3. Added quite a few parameters in application properties of order-service for resilience4j.
4. It is added in order service because, it is the one calling inventory service.
5. Notice the configuration where the entry ends with 'inventory', meaning it is uniquely identified such.


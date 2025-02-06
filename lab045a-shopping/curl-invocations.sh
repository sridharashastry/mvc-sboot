


########################FETCHING THE TOKEN ##################################



curl -X POST http://localhost:8181/realms/shoping-realm/protocol/openid-connect/token -d client_id=shopping-client1 -d client_secret=KA1TvT76ojagwUqbJazPSs9YnQpwMs2j -d grant_type=client_credentials



###Get orders

curl -i -X GET http://localhost:8080/api/product -H "Authorization: Bearer xxxxxx"


###Place orders



curl -X POST http://localhost:8080/api/order -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGxxxxxxx" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2} ]}'



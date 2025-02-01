

######################PRODUCTS SERVICE===================


#Given spring cloud gateway is enabled with security, all the downstream applications now become secured.

#For instance product service endpoint as below cannot be accessed without a bearer token and we will get authentication error.

#Notice error for below invocation

PS C:\ws\sboot> curl -i -X GET http://localhost:8080/api/product
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Bearer
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 0
Referrer-Policy: no-referrer
content-length: 0

PS C:\ws\sboot>


########################FETCHING THE TOKEN ##################################



curl -X POST http://localhost:8181/realms/shoping-realm/protocol/openid-connect/token -d client_id=shopping-client1 -d client_secret=KA1TvT76ojagwUqbJazPSs9YnQpwMs2j -d grant_type=client_credentials

{"access_token":"eyJxxxxxxxxxw","expires_in":300,"refresh_expires_in":0,"token_type":"Bearer","not-before-policy":0,"scope":"email profile"}
PS C:\ws>





########################USING THE TOKEN TO GET ALL THE PRODUCTS ##################################

PS C:\ws>
PS C:\ws>
PS C:\ws> curl -i -X GET http://localhost:8080/api/product -H "Authorization: Bearer xxxxxx"
HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: application/json
Date: Thu, 30 Jan 2025 21:50:07 GMT
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 0
Referrer-Policy: no-referrer


[{"id":"6794f07672a1bd0f38304851","name":"Phone","description":"IPhone","price":110.99}]
PS C:\ws>


########################ORDER SERVICE INVOCATION USING  THE TOKEN ##################################

####FETCH TOKEN FOR GETTING THE ORDERS


 curl -X POST http://localhost:8181/realms/shoping-realm/protocol/openid-connect/token -d client_id=shopping-client1 -d client_secret=KA1TvT76ojagwUqbJazPSs9YnQpwMs2j -d grant_type=client_credentials

{"access_token":"eyJhbGXXXXXa5UVA","expires_in":300,"refresh_expires_in":0,"token_type":"Bearer","not-before-policy":0,"scope":"email profile"}

#USE THE TOKEN  TO GET THE REGISTERED ORDERS


curl -X GET "http://localhost:8080/api/order" -H "Authorization: Bearer eyJhXXXXXX"



[{"id":1,"orderNumber":"f1b8fbd4-6e8c-424f-9602-0ba8923bfeb4","orderLineItemList":[{"id":1,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":2,"orderNumber":"a07b8c23-fc89-4f37-b0b6-1a0881c37ff4","orderLineItemList":[{"id":2,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":52,"orderNumber":"980fd410-b01c-43eb-bfa2-21ff6a7ef6fe","orderLineItemList":[{"id":52,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":53,"orderNumber":"27fd3b6f-f874-4a0e-8e89-06d4f3c91cf0","orderLineItemList":[{"id":53,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":54,"orderNumber":"419f39e7-e92a-4529-98ed-ecaca4d7bd7b","orderLineItemList":[{"id":54,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":55,"orderNumber":"9d49198a-2230-4bd9-9906-6316e0e9ab77","orderLineItemList":[{"id":55,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":56,"orderNumber":"a3f62e92-cdd4-466d-9979-6e63099dd286","orderLineItemList":[{"id":352,"skuCode":"iphone12","price":100.50,"quantity":2}]}]
PS C:\ws>



####USING THE TOKEN TO PLACE A NEW ORDER

curl -X POST http://localhost:8080/api/order -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGxxxxxxx" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2} ]}'




[{"id":1,"orderNumber":"f1b8fbd4-6e8c-424f-9602-0ba8923bfeb4","orderLineItemList":[{"id":1,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":2,"orderNumber":"a07b8c23-fc89-4f37-b0b6-1a0881c37ff4","orderLineItemList":[{"id":2,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":52,"orderNumber":"980fd410-b01c-43eb-bfa2-21ff6a7ef6fe","orderLineItemList":[{"id":52,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":53,"orderNumber":"27fd3b6f-f874-4a0e-8e89-06d4f3c91cf0","orderLineItemList":[{"id":53,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":54,"orderNumber":"419f39e7-e92a-4529-98ed-ecaca4d7bd7b","orderLineItemList":[{"id":54,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":55,"orderNumber":"9d49198a-2230-4bd9-9906-6316e0e9ab77","orderLineItemList":[{"id":55,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":56,"orderNumber":"680b4bdb-47ae-48e0-9782-10786584a970","orderLineItemList":[{"id":252,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":302,"orderNumber":"fb921988-f9b4-4c56-bc8c-b84eb429ec2b","orderLineItemList":[{"id":302,"skuCode":"iphone12","price":100.5,"quantity":2}]}]
PS C:\ws>
PS C:\ws>
PS C:\ws>

Very important is have all the services up including mysql db and mongo db.




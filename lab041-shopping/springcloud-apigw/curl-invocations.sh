
#Given spring cloud gateway is enabled with security, all the downstream applications now become secured.

#For instance product service endpoint as below cannot be accessed without a bearer token and we will get authentication error.

#curl -i -X GET http://localhost:8080/api/product

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


########################PLACING AN ORDER USING THE TOKEN ##################################

PS C:\ws>
PS C:\ws>   curl -X POST http://localhost:8080/api/order -H "Content-Type: application/json" -H "Authorization: Bearer eyJhbGxxxxxxx" -d '{"orderLineItemDtoList":[{"skuCode":"iphone12","price":100.5,"quantity":2} ]}'




[{"id":1,"orderNumber":"f1b8fbd4-6e8c-424f-9602-0ba8923bfeb4","orderLineItemList":[{"id":1,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":2,"orderNumber":"a07b8c23-fc89-4f37-b0b6-1a0881c37ff4","orderLineItemList":[{"id":2,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":52,"orderNumber":"980fd410-b01c-43eb-bfa2-21ff6a7ef6fe","orderLineItemList":[{"id":52,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":53,"orderNumber":"27fd3b6f-f874-4a0e-8e89-06d4f3c91cf0","orderLineItemList":[{"id":53,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":54,"orderNumber":"419f39e7-e92a-4529-98ed-ecaca4d7bd7b","orderLineItemList":[{"id":54,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":55,"orderNumber":"9d49198a-2230-4bd9-9906-6316e0e9ab77","orderLineItemList":[{"id":55,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":56,"orderNumber":"93792a62-25c5-454c-bdf6-02a076249e6c","orderLineItemList":[{"id":56,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":57,"orderNumber":"29e8dd99-d2ea-4cd1-ab89-dca26afff77c","orderLineItemList":[{"id":57,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":58,"orderNumber":"5c95f7eb-76de-4bb5-9209-d297a5a86f68","orderLineItemList":[{"id":58,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":59,"orderNumber":"e6b29881-6220-4845-8fae-9a73e19cae51","orderLineItemList":[{"id":59,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":102,"orderNumber":"38d85d58-d3de-4716-aaaa-1b36f1351ab5","orderLineItemList":[{"id":102,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":103,"orderNumber":"77181d80-02d0-4aef-b0da-cc9b6840dcee","orderLineItemList":[{"id":103,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":104,"orderNumber":"1ee2b938-24ad-4608-b82d-880555235168","orderLineItemList":[{"id":104,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":105,"orderNumber":"9e22b717-cb30-4e85-a6dc-b9db994e05bc","orderLineItemList":[{"id":105,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":152,"orderNumber":"29a72be4-0b46-46f1-991c-5830424b32a7","orderLineItemList":[{"id":152,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":202,"orderNumber":"e9a252fa-e153-4efa-9f4d-b02715af1826","orderLineItemList":[{"id":202,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":252,"orderNumber":"680b4bdb-47ae-48e0-9782-10786584a970","orderLineItemList":[{"id":252,"skuCode":"iphone12","price":100.50,"quantity":2}]},{"id":302,"orderNumber":"fb921988-f9b4-4c56-bc8c-b84eb429ec2b","orderLineItemList":[{"id":302,"skuCode":"iphone12","price":100.5,"quantity":2}]}]
PS C:\ws>
PS C:\ws>
PS C:\ws>

Very important is have all the services up including mysql db and mongo db.



####Accessing countries 'WITH' credentials


PS C:\ws> curl -i -X GET "http://localhost:8080/countries" -u admin:admin
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 12 Jan 2025 13:00:05 GMT

[{"id":1,"name":"Portugal","code":"PT","population":100},{"id":2,"name":"India","code":"IN","population":200}]


####Accessing countries 'WITHOUT' credentials

PS C:\ws> curl -i -X GET "http://localhost:8080/countries"
HTTP/1.1 401
WWW-Authenticate: Basic realm="Realm"
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Sun, 12 Jan 2025 13:00:11 GMT

PS C:\ws>

###Accessing the default landing page without credentials. Will get error

PS C:\ws> curl -i -X GET "http://localhost:8080/"
HTTP/1.1 401
WWW-Authenticate: Basic realm="Realm"
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Sun, 12 Jan 2025 12:50:44 GMT


####Access with credentials

PS C:\ws> curl -i -X GET "http://localhost:8080/" -u admin:admin
HTTP/1.1 200
Set-Cookie: JSESSIONID=B0B85D33DA8B8244A6209FAF55979EEC; Path=/; HttpOnly
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 62
Date: Sun, 12 Jan 2025 12:51:50 GMT

Hello World. Your session id :B0B85D33DA8B8244A6209FAF55979EEC
PS C:\ws>



####Accessing countries post (save) endpoint to save



PS C:\ws> curl -v -i -X POST "http://localhost:8080/countries" -H "Content-Type: application/json" -d '{"name":"USA","code":"US","population":300}' -u admin:admin
Note: Unnecessary use of -X or --request, POST is already inferred.
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
* Server auth using Basic with user 'admin'
> POST /countries HTTP/1.1
> Host: localhost:8080
> Authorization: Basic YWRtaW46YWRtaW4=
> User-Agent: curl/8.9.1
> Accept: */*
> Content-Type: application/json
> Content-Length: 43
>
* upload completely sent off: 43 bytes
< HTTP/1.1 200
HTTP/1.1 200
< X-Content-Type-Options: nosniff
X-Content-Type-Options: nosniff
< X-XSS-Protection: 0
X-XSS-Protection: 0
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
Pragma: no-cache
< Expires: 0
Expires: 0
< X-Frame-Options: DENY
X-Frame-Options: DENY
< Content-Type: application/json
Content-Type: application/json
< Transfer-Encoding: chunked
Transfer-Encoding: chunked
< Date: Sun, 12 Jan 2025 13:06:08 GMT
Date: Sun, 12 Jan 2025 13:06:08 GMT
<

[{"id":1,"name":"Portugal","code":"PT","population":100},{"id":2,"name":"India","code":"IN","population":200},{"id":0,"name":"USA","code":"US","population":300}]* Connection #0 to host localhost left intact
PS C:\ws>

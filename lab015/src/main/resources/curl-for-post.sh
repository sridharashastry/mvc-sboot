######   Fetch CSRF Token AND SESSION ID ###################


PS C:\ws>  # Step 1: Fetch the CSRF token and session cookie
PS C:\ws> curl -i -X GET "http://localhost:8080/csrftoken" -u admin:admin
HTTP/1.1 200
Set-Cookie: JSESSIONID=CCF62A93CC09F3D3DA3BA096B54C17F4; Path=/; HttpOnly
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sat, 11 Jan 2025 20:27:30 GMT

{"token":"HMm4E5zgHEAGciclaSeJ_p6TxUqtrW2gW_Ell3l0SWexs8jkJfqMdavWenYrExUQUQq9ya2n6HOYnVqNaMIR9EEXelOEh_vd","parameterName":"_csrf","headerName":"X-CSRF-TOKEN"}
PS C:\ws>






######   Fetch CSRF Token ###################




PS C:\ws> # Step 2: Use the obtained CSRF token and session cookie for the POST request
PS C:\ws> curl -i -X POST "http://localhost:8080/countries" -H "Content-Type: application/json" -H "X-CSRF-TOKEN:<CSRF-TOKEN>" -d '{"id":3,"name":"USA","code":"US","population":300}' -u admin:admin --cookie "JSESSIONID=<SESSION-ID>"
HTTP/1.1 401
Set-Cookie: JSESSIONID=25498EB9E6BC415512D59BAA154E2744; Path=/; HttpOnly
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
WWW-Authenticate: Basic realm="Realm"
Content-Length: 0
Date: Sat, 11 Jan 2025 20:27:30 GMT




######   Add the country using  CSRF Token ###################



PS C:\ws> curl -v -i -X POST "http://localhost:8080/countries" -H "Content-Type: application/json" -H "X-CSRF-TOKEN: HMm4E5zgHEAGciclaSeJ_p6TxUqtrW2gW_Ell3l0SWexs8jkJfqMdavWenYrExUQUQq9ya2n6HOYnVqNaMIR9EEXelOEh_vd" -d '{"id":3,"name":"USA","code":"US","population":300}' -u admin:admin --cookie "JSESSIONID=CCF62A93CC09F3D3DA3BA096B54C17F4"
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
> Cookie: JSESSIONID=CCF62A93CC09F3D3DA3BA096B54C17F4
> Content-Type: application/json
> X-CSRF-TOKEN: HMm4E5zgHEAGciclaSeJ_p6TxUqtrW2gW_Ell3l0SWexs8jkJfqMdavWenYrExUQUQq9ya2n6HOYnVqNaMIR9EEXelOEh_vd
> Content-Length: 50
>
* upload completely sent off: 50 bytes
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
< Date: Sat, 11 Jan 2025 20:28:25 GMT
Date: Sat, 11 Jan 2025 20:28:25 GMT
<

[{"id":1,"name":"Portugal","code":"PT","population":100},{"id":2,"name":"India","code":"IN","population":200},{"id":3,"name":"USA","code":"US","population":300}]* Connection #0 to host localhost left intact
PS C:\ws>




######   List the countries ###################



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
Date: Sat, 11 Jan 2025 20:31:13 GMT

[{"id":1,"name":"Portugal","code":"PT","population":100},{"id":2,"name":"India","code":"IN","population":200},{"id":3,"name":"USA","code":"US","population":300}]
PS C:\ws>

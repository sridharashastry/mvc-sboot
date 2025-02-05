curl -X POST "http://localhost:8080/payments/ussd" -H "Content-Type: application/json" -d '{"paymentMethod":"USSD","amount":100.0,"currency":"USD","customerId":"12345"}'


curl -X GET "http://localhost:8080/payments/list"

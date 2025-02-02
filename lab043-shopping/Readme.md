1. Timeout added in properties of order_service
2. Added CompletableFuture in controller
3. Touched inventory service to add a thread.sleep for 10 seconds so that order_service can timeout.
docker ps


docker compose up

compose file with application properties
mvn spring-boot:run
(show console log)

Ctrl+C

compose file without application properties
mvn spring-boot:run
(show console log)

Ctrl+C

docker compose down
docker ps

mvn spring-boot:run
(show console log)

Optionals:
Web Access: http://localhost:8090/demo/connection-details
Docs: Development-Time Services
https://docs.spring.io/spring-boot/reference/features/dev-services.html#features.dev-services.docker-compose

show predefined set of supported applications
show labels to support "own" backends
show ignore label
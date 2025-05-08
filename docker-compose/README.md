# Scenario 1: Traditional way
1. Add the configuration to the application.properties file (postgres connexion)
2. Start the docker compose manually `docker compose up -d`
3. Run the application with `mvn spring-boot:run`
4. Run the different requests from the file `requests.http`

Stop everything

# Scenario 2: Remove config, magic happens
1. Remove the configuration from the application.properties file
2. Start the docker compose manually `docker compose up -d`
3. Run the application with `mvn spring-boot:run`
4. Run the different requests from the file `requests.http`

5. Stop everything again

# Scenario 3: Superpower mode!
1. Remove the configuration from the application.properties file
2. DON'T start the docker compose (check it is not running)
3. Run the application with `mvn spring-boot:run`
4. Run the different requests from the file `requests.http`
# Run the Application y curl to the hello endpoint
`mvn spring-boot:run`
Go to `requests.http` file and run the `GET` request to the `/hello` endpoint. You should see a response like this:
```json
{
  "message": "Hello from Spring! 👋"
}
```

# Compile JAR and Native Image and Test
1. Package JAR
`mvn clean package`
2. Compile Native Image
`mvn -Pnative native:compile`

This will generate:
`target/graalvm-1.0-SNAPSHOT.jar` (JAR file)
`target/graalvm` (binary file)

# Measure times
| Image Type   | Command                                                                                          | Time |
|--------------|--------------------------------------------------------------------------------------------------|------|
| JAR          | `time java -jar target/graalvm-0.0.1-SNAPSHOT.jar`                                   | 0.Xs |
| Native Image | `time ./target/graalvm`                                                            | 0.Xs |
| Docker with JAR | `docker build -t graalvm-jar-demo . time docker run graalvm-jar-demo`                            | 0.Xs |
| Docker with Native Image | `docker build -f Dockerfile.native -t graalvm-native-demo . time docker run graalvm-native-demo` | 0.Xs |
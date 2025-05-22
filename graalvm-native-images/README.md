## Build Options

### Traditional JAR
```mvn clean package```

### Build Native Image
Pre-requisites:
- Install GraalVM on your local machine
- Add Spring Boot GraalVM dependencies

```mvn -Pnative native:compile```

### Build JAR inside a Docker Image
```mvn spring-boot:build-image```

### Build Native Image inside a Docker Image
```mvn spring-boot:build-image -Pnative -Dspring-boot.build-image.imageName=graalvm-demo:native```

### Build with Pack and Java 21
```
pack config default-builder paketobuildpacks/builder-jammy-tiny
pack build graalvm-native-images \
--builder paketobuildpacks/builder-jammy-tiny \
--env BP_NATIVE_IMAGE=true
```

### Comparing built images
```docker images | grep graalvm```

## Run Options

### Run Traditional JAR
```java -jar target/graalvm-0.0.1-SNAPSHOT.jar```

### Run Native Image
```./target/graalvm```

### Run JAR inside a Docker Image
```docker run graalvm:0.0.1-SNAPSHOT```

### Run Native Image inside a Docker Image
```docker run graalvm-demo:native```

# Measure Startup times
| Image Type   | Time |
|--------------|------|
| JAR          | 0.Xs |
| Native Image | 0.Xs |
| Docker with JAR  | 0.Xs |
| Docker with Native Image  | 0.Xs |
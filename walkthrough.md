## Build part

### Key things to say

- There are multiple ways to bring a Spring Boot app into a container
- The most common one is Dockerfiles. They are either simple and not very optimized or very optimized and not simple :-)
- Paketo is a CNCF Buildpacks implementation from Cloud Foundry. It provides a simple and optimized container build mechanism
- Keep your base image consistent, optimize on JRE (custom modules), slice application layer

### Key things to demo (optional)

- Show container images
- Show docker history for optimized and non optimized files

## Build part - GraalVM

### Key things to say

- Native images and container images are both called images, but not the same thing
- Similarity: Both contain everything they need to execute
- Difference: Container aim for compatibility across platforms, Native aim for optimization for a specific platform
- Native images can be packed into container images
- Optimizations of previous steps don't apply here, Buildpacks work the same way

### Key things to demo

## Testing part

### Key things to say

- Using container for testing is probably mostly used case
- Binding backends, e.g. databases, messaging etc. as containers
- 3 different options
    - manual wiring
    - Docker Compose
    - Testcontainer
- Difference is where and how the config is being set
    - manual
    - in compose.yaml
    - in Java Code

## Docker Compose

### Key things to say

- see above - config in compose file
- no modification in source code or properties

### Key things to demo

- Automatic container start, if not present
- pom.xml dependency
- Optional: Service Connection

## Testcontainer

### Key things to say

- config in source code + testcontainer dependency
- older and newer style

### Key things to demo

- show old style container with manual properties and new @service connection ones
- show how they quickly spin up and tear down again
- show main method in test part
- show mvn test:run

## Spring AI part

### Key things to say

- Docker Model Runner treats local LLMs very similar to normal containers
- Can be attached to Spring AI functionality easily
- Docker Compose is also supported for models (but not particularly with similar Spring Boot Support)
- TestContainers can also be used with socat "Proxy Container"

### Key things to demo

- run Spring AI app in normal mode and test:run mode to show usage of different LLMs with different config

## Kubernetes part

- tbd

### Key things to say

### Key things to demo

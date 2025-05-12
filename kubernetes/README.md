# Prerequisites
Check that the native image is already created `./mvnw -Pnative native:compile` ./mvnw package -Pnative
Be sure that the docker image is built `docker build -f Dockerfile.native -t kubernetes:latest .`
Check that the kubernetes context is set to the correct cluster `kubectl config use-context docker-desktop`
`kubectl get nodes`

# Create the application
`kubectl apply -f k8s/`
This will create the following resources:
- deployment.yaml: creates the pod with your container and configures the liveness/readiness probes.
- service.yaml: exposes the service on port 80 (LoadBalancer type).
- hpa.yaml (optional): enables autoscaling.


kubectl config use-context docker-desktop
mvn clean package -DskipTests
docker build -t kubernetes-demo:latest .
docker images | grep kubernetes-demo
kubectl apply -f k8s/
kubectl get all -l app=kubernetes-demo
kubectl port-forward deployment/kubernetes-demo 8080:8080

## Controllers and Custom Health Indicators 
- Health Simulator Controller
- Hello World Controller
- Custom Liveness Health Indicator
- Custom Readiness Health Indicator

## Build and Deploy
```
mvn clean package -DskipTests
docker build -t kubernetes-demo:latest .
kubectl apply -f k8s/
kubectl get pods -w
```

## Simulate readiness down
Check the pods, it should be in a crash loop, not ready.
Check the recurrent call where the traffic is balanced between the available and healthy pods.
```
curl -X POST http://localhost:8080/internal/readiness/false
```
## Port forward
```
kubectl port-forward deployment/kubernetes-demo 8080:8080
```

## Simulate liveness down
See how it is automatically recovered and available again
```
http://localhost:80/simulate/liveness/down
```

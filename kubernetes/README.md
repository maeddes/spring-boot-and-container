# Pre requisittes
`mvn clean package -DskipTests`
`docker build -t kubernetes-demo .`
`docker run --rm -p 8080:8080 kubernetes-demo`
`curl http://localhost:8080/actuator/health`

# Kubernetes
## Deploy the app in Kubernetes
```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```
## Check the status of the deployment
```bash
kubectl get pods -l app=kubernetes-demo -w
```

## Check the logs of the container (other terminal)
```bash
kubectl logs -f deploy/kubernetes-demo
``` 

## Test the endpoint
```bash
kubectl port-forward svc/kubernetes-demo 8080:80
curl http://localhost:8080/actuator/health
```

## Simulate a failure
```bash
kubectl exec deploy/kubernetes-demo -- pkill -f java
// still watching the logs
kubectl get pods -l app=kubernetes-demo -w
```

## Simulate not to be ready
```bash
kubectl patch deployment kubernetes-demo \
  -p '{"spec":{"template":{"metadata":{"annotations":{"demo":"force-unready"}}}}}'
// check the logs
kubectl get endpoints kubernetes-demo
```

### Visual summary
```bash
watch kubectl get pods -l app=kubernetes-demo
```

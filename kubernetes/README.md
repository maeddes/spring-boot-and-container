mvn clean package -DskipTests
docker build -t kubernetes-demo:latest .
docker images | grep kubernetes-demo
kubectl apply -f k8s/
kubectl get all -l app=kubernetes-demo
kubectl port-forward deployment/kubernetes-demo 8080:8080

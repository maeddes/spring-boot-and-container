#!/bin/bash

# reset-demo-env.sh
# Cleans up the environment between Spring.io demo stages while keeping prebuilt Docker images.

set -e

MODE="$1"

if [[ -z "$MODE" ]]; then
  echo "Usage: ./reset-demo-env.sh [compose|k8s|manual]"
  exit 1
fi

echo "==> Resetting environment for: $MODE"

# Step 1: Stop running containers
echo "-- Stopping running containers..."
docker ps -q | xargs -r docker stop

# Step 2: Remove stopped containers
echo "-- Removing stopped containers..."
docker ps -aq | xargs -r docker rm

# Step 3: Remove custom Docker networks (skip default networks)
echo "-- Removing custom Docker networks..."
docker network ls --filter "type=custom" -q | xargs -r docker network rm

# Step 4 (optional): Remove volumes if --with-volumes is passed
if [[ "$2" == "--with-volumes" ]]; then
  echo "-- Removing Docker volumes..."
  docker volume ls -q | xargs -r docker volume rm
fi

# Step 5: Kubernetes cleanup if mode is k8s
if [[ "$MODE" == "k8s" ]]; then
  echo "-- Cleaning up Kubernetes resources..."
  kubectl delete all --all || true
  kubectl delete pvc --all || true
  kubectl delete configmap --all || true
  kubectl delete secret --all || true
fi

echo "✅ Environment reset complete. Ready for demo: $MODE"

#!/bin/bash

LOG_FILE="container_build.log"
echo "🚀 Starting Docker builds..." > "$LOG_FILE"
echo "Build started at: $(date)" >> "$LOG_FILE"
echo "---------------------------------" >> "$LOG_FILE"

# Loop through each Dockerfile
for file in Dockerfile*; do
  tag=${file#Dockerfile}
  tag=${tag#-}
  [[ -z "$tag" ]] && tag="default"

  echo "🔧 Building Docker image with tag: $tag"
  echo "Building $file as java-image:$tag" >> "$LOG_FILE"

  if docker build -f "$file" -t "java-image:$tag" .; then
    echo "✅ SUCCESS: $file built as java-image:$tag" >> "$LOG_FILE"
  else
    echo "❌ FAILURE: $file failed to build" >> "$LOG_FILE"
  fi

  echo "---------------------------------" >> "$LOG_FILE"
done

# Add a final summary of built images to the log file
echo "📦 Final Docker images list:" >> "$LOG_FILE"
docker images >> "$LOG_FILE"

echo "✅ Build process completed. Check $LOG_FILE for details."


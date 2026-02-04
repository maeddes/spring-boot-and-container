docker desktop enable model-runner --tcp=12434 

docker model pull ai/smollm2
docker model pull ai/functiongemma
docker model pull ai/ministral3
docker model pull ai/gemma3

mvn spring-boot:run

http :8080/chat message=="what's your name?"

mvn spring-boot:test-run

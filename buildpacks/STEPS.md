

pack build samples/java --env BP_JVM_JLINK_ENABLED=true --env BP_JVM_JLINK_ARGS="--no-header-files --compress=1 --add-modules java.base,java.se"

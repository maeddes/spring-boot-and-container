cd buildpacks 

pack config default-builder paketobuildpacks/builder-jammy-tiny  

pack build java-image:paketo

mvn clean package

jdeps --ignore-missing-deps \
    --recursive  \
    --multi-release 21  \
    --print-module-deps  \
    --class-path 'BOOT-INF/lib/*'  \
    target/simplecode-0.0.1-SNAPSHOT.jar

pack build java-image:paketo-jlink --env BP_JVM_JLINK_ENABLED=true --env BP_JVM_JLINK_ARGS="--no-header-files --compress=1 --add-modules java.base"

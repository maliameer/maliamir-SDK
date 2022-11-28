# API-SDK Implementation

'src' folder holds API-SDK implementation for specs in https://liblab.com/take-home-project.

Requirements:
- JDK8: Please make sure environment variable: JAVA_HOME is set after JDK8 installation.
- Maven: Download maven from https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip and explode it to any directory. Please set "<maven_dir>/bin" in your environment variable: PATH.
- As per https://the-one-api.dev/, please sign up to get API Access Token then put it in file: src/main/resources/sdk.properties.

Execution:
- mvn package

Above command will run all Unit Tests and generate jar: "api-sdk-1.0.jar" in directory: "target".


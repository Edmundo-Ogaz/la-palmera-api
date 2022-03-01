build:
	mvn package

run: build
	mvn spring-boot:run

#gradlew-build:
#    ./gradlew build

#gradlew-run:
#    ./gradlew bootRun

#jar-run-gradlew:
#    java -jar build/libs/gs-rest-service-0.1.0.jar

mvn-build:
	mvn package

mvn-run:
	mvn spring-boot:run

#jar-run-mvn:
#    java -jar target/gs-rest-service-0.1.0.jar

dependencies:
	mvn dependency:tree

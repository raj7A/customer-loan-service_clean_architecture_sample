build:
	echo "Building JAR file ..."
	./mvnw --projects entities,usecases,interfaceadapters,drivers clean install -DskipTests=true
run-all-tests:
	echo "Running Tests ..."
	./mvnw test
compile:
	echo "Compiling ..."
	./mvnw compile
run-integration-tests:
	echo "Running integration tests ..."
	./mvnw --projects drivers,integration-tests test
run-functional-tests:
	echo "Running functional tests ..."
	./mvnw --projects drivers,functional-tests test
run-unit-tests:
	echo "Running functional tests ..."
	./mvnw --projects entities,usecases,interfaceadapters test
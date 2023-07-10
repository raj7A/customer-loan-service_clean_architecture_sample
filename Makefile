build:
	echo "Building JAR file ..."
	mvn --projects entities,usecases,interfaceadapters,drivers clean install -DskipTests=true
run-all-tests:
	echo "Running Tests ..."
	mvn test
compile:
	echo "Compiling ..."
	mvn compile
run-integration-tests:
	echo "Running integration tests ..."
	mvn --projects drivers,integration-tests test
run-functional-tests:
	echo "Running functional tests ..."
	mvn --projects drivers,functional-tests test
run-unit-tests:
	echo "Running functional tests ..."
	mvn --projects entities,usecases,interfaceadapters test
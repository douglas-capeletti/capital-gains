PROJECT_NAME=capital-gains
.PHONY: all clean build

docker:
	make docker-build
	make docker-run

docker-build:
	docker build -t ${PROJECT_NAME} .

docker-run:
	docker run -it ${PROJECT_NAME}

docker-run-debug:
	docker run -it ${PROJECT_NAME} -d

build:
	./gradlew jar

run:
	java -jar ./build/libs/${PROJECT_NAME}-1.0.jar

run-debug:
	java -jar ./build/libs/${PROJECT_NAME}-1.0.jar -d

test:
	./gradlew test

zip:
	git archive --format=zip --output=./${PROJECT_NAME}.zip HEAD
unzip:
	rm -rf ${PROJECT_NAME}/
	mkdir ${PROJECT_NAME}
	unzip ${PROJECT_NAME}.zip -d ${PROJECT_NAME}/
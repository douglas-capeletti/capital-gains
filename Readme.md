## Capital Gains

## Running on Docker

---

### Build the container image

``` sh
docker build -t capital-gains .
```

### Run the container image on iterative mode

``` sh
docker run -it capital-gains
```

### Run the container image sending an input file

``` sh
docker run -i capital-gains < src/main/resources/input/case-all
```

## Running on local env

### Build the project

```sh
./gradlew jar
```

### Then run the jar file

```sh
java -jar ./build/libs/capital-gains-1.0.jar
# type the input and then click <return>
```

### Running with an input file

```sh
java -jar ./build/libs/capital-gains-1.0.jar < src/main/resources/input/case-all
# To stop, use: (CTRL + C) 
```

### How to run the tests

```sh
./gradlew test
```

### How tests are organized

- Tests are generated dynamically based on input files.
- Both input and output have the same name but placed into their respective folders on project resources
- To create a new test you just new to:
  - Put a valid input file into the input folder 
  - Put a valid output file into the output folder
  - BOTH MUST HAVE THE SAME NAME otherwise, the test will fail.

```yaml
resources:
  input:
    case1
  output:
    case1
```

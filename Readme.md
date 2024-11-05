## Capital Gains

## Using Makefile

``` sh
make docker #build and run the project
make docker-build
make docker-run
make docker-run-debug
make build
make run # < src/main/resources/input/case-all
make run-debug
make test
```

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

## Debugging

For debugging the application just add the "-d" parameter

``` sh
java -jar ./build/libs/capital-gains-1.0.jar -d
# or 
docker run -it capital-gains -d
```

## How to run the tests

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

But, why this way? The tests are basically the same,
so with this structure we can validate the whole process of parsing input, calculate, parse output. (integration test
look-alike)

With this structure the tests are easily/dynamically extensible, so you can create test files and don't need to do code.

```yaml
resources:
  input:
    case1
  output:
    case1
```

## Project Structure & Project Decisions

The entrypoint is the `App` class there it's created the application loop and the application Runner, in this case
`CliRunner`.

Let's see the structure

```yaml
App: "Main class, will choose the application configuration and start the app"

application: "Classes responsible for the application execution (kinda obvious, sorry)"
  - CliRunner: "Responsible for run the cli"
  - ObjectParser: "Definition about how to parse an object"
  - SimulationContext: "Holds and state of the simulation execution, in the future some config.properties"
  - UserInterface: "Definition about how to handle UI data"
  - Simulator: "This class will process simulation transactions and hold the state changes (will run the app logic)"

domain: "Classes containing business rules (how buy/sell should work)"
  operations:
    - Buy: "Buy business rules"
    - Sell: "Sell business rules"
  - OperationFactory: "Dynamically instantiate operations based on input"
  - Operation: "Operation execution definition"
  - ResultTax: "Wrapper object for the result tax of operations"
  - Simulation: "Simulation definition, an list of Transactions"
  - SimulationState: "Immutable application state, keep the values for simulation execution"
  - Transaction: "Operation transaction, operation type (buy/sell) and their values"

infrastructure: "Classes of adapters, sets of different infrastructure possibilities"
  ObjectParsers: "Parsers for the application objects based on the choosed data types (Json, Xml, yaml)"
  userInterfaces: "Parsers of how to handle data on a certain user interface (CLI, GUI)"

```

The main idea here was to decouple the application layers as much as possible from inside to outside.
Let me explain, the domain classes doesn't import anything from any other layer, so they are completely isolated, 
we can change anything else without affect the business logic.

The Application layer do know about the domain, but not know about the chosen infrastructure, 
the application layers is responsible to run the application with any infrastructure chosen.

Otherwise, the Infrastructure layer know about all the others and implements the interfaces to handle the app behavior,
without being coupled on the project dependencies, and easy to change in the future.

## Troubleshoot
During the development I had an issue while generating the docker image, and then notice that was the `./gradlew` file, 
so if you face an issue while building the project or project image, see the first line of `./gradlew` file and make sure it's pointing to the correct path to your sh/shel   





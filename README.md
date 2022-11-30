# Cake Quarkus

A simple application to manage delicious tasty cakes that illustrates a domain-centric architecture (cf. hexagonal
architecture, ports and adapters, clean architecture) using [Quarkus](https://quarkus.io).

## Developing the application

Requires Java 17. Building and testing a native container image requires [Docker](https://www.docker.com).

To start the application on port `8080`:

```shell
./gradlew quarkusDev
```

You can then:

* View all cakes <http://localhost:8080/cakes>
* View the Open API documentation <http://localhost:8080/q/swagger-ui/>
* View the Quarkus dev UI <http://localhost:8080/q/dev/>

To format code, run tests and checks:

```shell
./gradlew spotlessApply check
```

To package the application as a native executable in a container image and run integration tests against it:

```shell
./gradlew build quarkusIntTest
```

To run the resulting container image:

```shell
docker run -i --rm -p 8080:8080 waracle/cake:1.0-SNAPSHOT
```

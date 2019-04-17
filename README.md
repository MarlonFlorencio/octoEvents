# Octo Events

Octo Events is an application that listens to Github Events via webhooks

## Artifacts 

  - [Kotlin](https://github.com/JetBrains/kotlin) as programming language
  - [Javalin](https://github.com/tipsy/javalin) as web framework
  - [Koin](https://github.com/InsertKoinIO/koin) as dependency injection framework
  - [Exposed](https://github.com/JetBrains/Exposed) as Sql framework to persistence layer
  - [Jackson](https://github.com/FasterXML/jackson-module-kotlin) as data bind serialization/deserialization
  - [H2](https://github.com/h2database/h2database) as database test
  - [Postgres](https://github.com/postgres/postgres) as database
  - [Khttp](https://github.com/jkcclemens/khttp) as HTTP client

## Test

Run tests

```bash
$ mvn clean test
```

## Execution

##### Prerequisite

Postgres database conection. The application will look for the following location:
```bash
//localhost:5432/postgres
```
This url, user and password can be changed in the following file:
```bash
src/main/resources/koin.properties
```

##### Start Server
```bash
$ mvn clean install
$ java -jar target/octoEvents-1.0.0-jar-with-dependencies.jar 
```

##### Post Event
```bash
$ curl -i -X POST  -H "Accept: Application/json" -H "Content-Type: application/json" http://localhost:7000/issues --data "@payload/post.json"
```

##### Get Events
```bash
$ curl -i -X GET http://localhost:7000/issues/99/events
```
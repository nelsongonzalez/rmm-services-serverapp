# Rmm Services

## Build & Run

To build and run the project you need to have **PostgreSQL 11**, **JDK 8** and **Git client**.

Also, you need to create two databases:

- `smart_services`
- `smart_services_test`

The databases are used for test and run the application.

Once you have created the databases change the connection properties in the follow `application.properties` files:
 
- `src/test/resources/application.properties`

- `src/main/resources/application.properties`

The properties that you have to change are:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/smart_services_test
spring.datasource.username=YOUR_USER_NAME
spring.datasource.password=YOUR_PASSWORD
```

When you have fulfilled these requirements please follow the next steps:

```
git clone https://github.com/nelsongonzalez/rmm-services-serverapp.git
cd rmm-services-serverapp/
./gradlew build
```

Before running the application you must run the script to create the tables in `smart_services` database:

- `src/main/resources/schema.sql` create the tables.

- `src/main/resources/data.sql` fills the basic data.

And then run `java -jar build/libs/rmm-services-0.0.1-SNAPSHOT.jar` to start the application.

The basic authentication credentials are: `nelson / password`

## REST Services documentation

The documentation about the services can be found here:

- [Swagger UI](http://localhost:8080/swagger-ui.html)

- [API Docs](http://localhost:8080/v2/api-docs)

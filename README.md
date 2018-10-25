# Rmm Services

## Build & Run

To build and run the project you need to have **PostgreSQL 11**, **JDK 8** and **Git client**.

Also, you need to create two databases:

- smart_services
- smart_services_test

The databases are user for test and run the application.

Once you have created the databases change the connection properties in the `application.properties` files:
 
- `src/test/resources/application.properties`

- `src/main/resources/application.properties`

The properties that you have to change are:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/smart_services_test
spring.datasource.username=nelson
spring.datasource.password=nelson
```

When you have fulfilled these requirements please follow the next steps:

```
git clone https://github.com/nelsongonzalez/rmm-services-serverapp.git
cd rmm-services-serverapp/
./gradlew build
```

To run the application run the script follow scripts on `smart_services` database:

- `src/main/resources/schema.sql`

- `src/main/resources/data.sql`

and then `java -jar build/libs/rmm-services-0.0.1-SNAPSHOT.jar`

# mutantscanner

This is a service to detect mutant genes in dna sequences.

## Build

To build the service run `gradle build`, this will generate a spring boot jar in build/libs/mutantscanner-service-1.0.0.jar

## Set up environment

By default the service connects to a postgres database in the localhost named **mutantscanner** using the user **mutantscanner** and password **mutantscanner**. You can override this configuration using the environment variables SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME and SPRING_DATASOURCE_PASSWORD. You need to initialize the database with the *schema.sql* found in the project.

## Run the service

To run the service from the source code you can execute:

```
$ gradle bootRun
```

Otherwise, you can run the jar:

```
$ java -jar mutantscanner-service-1.0.0.jar
```

## Service url

You can access the service on the following url: http://mribecky.com.ar:8080/mutant
# calls-web-service


## Running the application in dev mode


#### Pre-requisite: 
* Docker up and running

*   You can run the application in dev mode using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTES:_**  
> 
> Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.
> 
>  There is a Postman Collection in the `resources` directory that can be used to test the application.

> **_TODO\s:_**
>
> Testing, Logging, Exception Handling, Fault Tolerance, Documentation
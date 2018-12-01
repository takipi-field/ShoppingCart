To execute this ShoppingCartApplication from command prompt, you need a JDK and Maven

1.  Go to ShoppingCart folder and execute

mvn install:install-file -Dfile=takipi-sdk-0.2.0.jar -DgroupId=com.takipi -DartifactId=takipi-sdk -Dversion=0.2.0 -Dpackaging=jar -DgeneratePom=true

2.  Go to the ShoppingCart Folder and execute
```
mvn clean compile package
```
3.  
    * To run "All workflows", execute
    ```
    ./runAllWorkflows.sh
    ```

    * To run "Swallowed exceptions", execute
    ```
    ./runSwallowedExceptions.sh
    ```

    * To run "Uncaught Exceptions", execute
    ```
    ./runUncaughtExceptions.sh
    ```
  
Note that you can change these 3 properties in the scripts
```
run_mode=ALL_WORKFLOWS
no_of_threads=10 
no_of_iterations=10000
```
 
Valid values for run_mode are: `ALL_WORKFLOWS`/`SWALLOWED_EXCEPTION`/`UNCAUGHT_EXCEPTION`. Default is `ALL_WORKFLOWS`

You can also specify the OverOps ApplicationName and Deployment name from the scripts, e.g.
```
-Dtakipi.name=ShoppingCartApplication -Dtakipi.deployment.name=v1.0
```

4. You can also run this from maven command line using the below command
```
  	mvn exec:java
```

Good Luck.
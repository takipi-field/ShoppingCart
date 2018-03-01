To execute this ShoppingCartApplication from command prompt, you need a JDK and Maven

1. Go to the ShoppingCart Folder and execute
~/Documents/workspace/ShoppingCart> mvn clean compile package

2. a) To run "All workflows", execute
~/Documents/workspace/ShoppingCart> ./runAllWorkflows.sh
   b) To run "Swallowed exceptions", execute
   ~/Documents/workspace/ShoppingCart> ./runSwallowedExceptions.sh
   c) To run "Uncaught Exceptions", execute
   ~/Documents/workspace/ShoppingCart> ./runUncaughtExceptions.sh
  
 Note that you can change these 3 properties in the scripts
 	run_mode=ALL_WORKFLOWS
 	no_of_threads=10 
 	no_of_iterations=10000
 
 Valid values for run_mode are: ALL_WORKFLOWS/SWALLOWED_EXCEPTION/UNCAUGHT_EXCEPTIONS. Default is ALL_WORKFLOWS
 
 You can also specify the Overops ApplicationName and Deployment name from the scripts
 e.g.
 -Dtakipi.name=ShoppingCartApplication -Dtakipi.deployment.name=v1.0
 
 Good Luck.
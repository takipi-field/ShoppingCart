for i in {1..10}; do
	java -Xms4G -Xmx4G -agentlib:TakipiAgent -Dtakipi.name=ShoppingCartApplication -Dtakipi.deployment.name=v1.0 -jar target/shoppingCart.jar run_mode=UNCAUGHT_EXCEPTIONS no_of_threads=10 no_of_iterations=10000
done
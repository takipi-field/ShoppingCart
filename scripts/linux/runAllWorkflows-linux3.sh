java -Xms4G -Xmx4G -agentpath:/opt/takipi/lib/libTakipiAgent.so -Dtakipi.name=CMS -Dtakipi.deployment.name=v4.00 -Dtakipi.etl -Dtakipi.parallax -jar ../../target/shopping-cart-demo-1.0.jar run_mode=ALL_WORKFLOWS continue_running=true workflow1_enabled=true workflow2_enabled=true workflow3_enabled=true workflow4_enabled=true workflow5_enabled=true workflow6_enabled=true workflow7_enabled=true workflow8_enabled=true workflow9_enabled=false workflow10_enabled=false workflow11_enabled=false workflow12_enabled=false workflow13_enabled=true
package com.mockdata.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;

public class DelayGenerator {

	public static final Logger log = LoggerFactory.getLogger(DelayGenerator.class);

	private DelayGenerator() {
		//Private constructor to avoid instantiation
	}

	public static void introduceDelay(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			log.error("Thread is interrupted {}", e.getMessage());
			// Restore interrupted state...
		    Thread.currentThread().interrupt();
		}
	}

	public static void generateRandomDelay() {
		int randomNumber1 = RandomUtil.getRandomNumberInRange(0, 30);
		if (randomNumber1 == 10) {
			int randomNumber2 = RandomUtil.getRandomNumberInRange(2000, 5000);
			log.info("Introducing a Delay of: {}", randomNumber2);
			introduceDelay(randomNumber2);
		}
	}
}
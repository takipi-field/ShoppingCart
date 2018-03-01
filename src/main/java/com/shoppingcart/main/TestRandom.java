package com.shoppingcart.main;

import com.mockData.generate.utils.DateUtils;

public class TestRandom {

	public static void main(String[] args) {
		
		for (int i=0; i<100; i++) {
			System.out.println(DateUtils.getRandomDateString());
			
		}
	}
}

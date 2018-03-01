package com.mockData.generate;

import com.mockData.generate.utils.RandomUtil;

public class CustomerNotes {

	public String[] notes = {
						"This is a long time customer.", 
						"Customer had an bad experience with previous Order.",
						"Customer has cancelled previous 3 orders",
						"Platinum customer. Spends a lot of money with us.",
						"Customer has login issues with Website.",
						"Customer has visited online store mulitple times this week."
							};

	public String chooseCustomerNotes() {
		int index = RandomUtil.getRandomNumberInRange(1, notes.length+1);
		return notes[index];
	}
}

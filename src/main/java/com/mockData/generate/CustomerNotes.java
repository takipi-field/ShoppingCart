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

	public String getCustomerNotes() {
		int index = RandomUtil.getRandomNumberInRange(1, notes.length+2);
		if (index == notes.length+2) {
			return getExternalNote();
		} else {
			return notes[index];
		}
	}
	
	private String getExternalNote() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Getting an external note from :NoteService: failed.";
	}
}
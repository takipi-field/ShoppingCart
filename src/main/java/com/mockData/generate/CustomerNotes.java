package com.mockdata.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.utils.RandomUtil;

public class CustomerNotes {

	private static final Logger log = LoggerFactory.getLogger(CustomerNotes.class);

	private static final String[] notes = {
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
		log.info("Trying to get an external note");
		DelayGenerator.introduceDelay(1000);
		return "Getting an external note from :NoteService: failed.";
	}
}
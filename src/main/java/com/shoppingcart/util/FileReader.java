package com.shoppingcart.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mockdata.generate.DataTypeGenerator;
import com.shoppingcart.exception.ShoppingCartException;

public class FileReader {

	public static final Logger log = LoggerFactory.getLogger(DataTypeGenerator.class);

	private FileReader() {
		//Do nothing
	}

	public static List<String> get(String fileName) {
		try {
			String encoding = null;
			return FileUtils.readLines(
				new File(fileName), encoding);
		} catch (IOException e) {
			throw new ShoppingCartException(e);
		}
	}

	public static void write(File file, String contents) {
		log.info("Write a file: {} contents is: {}", file.getAbsolutePath(), contents);
		// NeedToDo write the file
	}
}

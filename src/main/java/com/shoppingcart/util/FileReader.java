package com.shoppingcart.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.shoppingcart.util.exception.ShoppingCartException;

public class FileReader {

	public static List<String> get(String fileName) {
		try {
			String encoding = null;
			List<String> lines = FileUtils.readLines(
				new File(fileName), encoding);
			return lines;
		} catch (IOException e) {
			throw new ShoppingCartException(e);
		}
	}

	public static void write(File file, String contents) {
		// TODO Auto-generated method stub
	}
}

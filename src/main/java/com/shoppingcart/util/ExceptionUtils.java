package com.shoppingcart.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String convertStackTraceToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		try {
			pw.flush();sw.flush();
			pw.close();sw.close();
		} catch (Exception ex) {
			//Eat it.
		}
		return sw.toString();
	}

}

package jp.co.geo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestSimpleDateFormat {
	public static void main(String arg[]) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		Date date = df.parse("Sun, 07 Nov 2010 05:24:39 +0000");
		System.out.println(date.toString());
	}
}

package jp.co.geo.logviewer.model;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogModel {
	
    MessageFormat format;
	
    Object[] message;
    
	public LogModel() {
        format = new MessageFormat("{0} {1} {2} [{3}] \"{4}\" {5} {6}");
	}
	
	
	public Object[] analyze(StringBuffer buffer) {
		return analyze(buffer.toString());
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public Object[] analyze(String str) {
		try {
			message = format.parse(str);
			return message;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Date getDate() {
		Date date = null;
		DateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
		ParsePosition pos = new ParsePosition(0);
		date = df.parse((String) message[3], pos);
		return date;
	}
	
	public String getURL() {
		return (String) message[4];
	}
	

	/**
	 * HTTPステータスコードを取得する
	 * @return
	 */
	public String getHttpStatusCode() {
		return (String) message[5];
	}
	
	public String getProcessingTime() {
		return (String) message[6];
	}
}

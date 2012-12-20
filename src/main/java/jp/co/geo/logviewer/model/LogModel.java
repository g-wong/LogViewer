package jp.co.geo.logviewer.model;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogModel {
	
    LogFormat format;
	
    Object[] message;
    
	public LogModel(LogFormat format) {
		this.format = format;
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
		message = format.parse(str);
		return message;
	}
	
	
	public Date getDate() {
		Date date = null;
		DateFormat df = new SimpleDateFormat(format.getTimeFormat(), Locale.ENGLISH);
		ParsePosition pos = new ParsePosition(0);
		date = df.parse((String) message[LogItemType.TIME.index()], pos);
		return date;
	}
	
	public String getURL() {
		return (String) message[LogItemType.URL.index()];
	}
	

	/**
	 * HTTPステータスコードを取得する
	 * @return
	 */
	public String getHttpStatusCode() {
		return (String) message[LogItemType.STATUS.index()];
	}
	
	public String getProcessingTime() {
		return (String) message[LogItemType.PROCESSING_TIME.index()];
	}
}

package jp.co.geo.logviewer.model;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

abstract class LogFormat {
	private String format;
	private MessageFormat messageFormat;
	private String timeFormat;
	protected ArrayList<LogItemType> types = new ArrayList<LogItemType>();
	private Object[] message;
	
	LogFormat(String format, String timeFormat, ArrayList<LogItemType> types) {
		this.format = format;
		this.messageFormat = new MessageFormat(format);
		this.timeFormat = timeFormat;
		this.types = types;
	}
	
	public void setFormat(String format) {
		this.messageFormat = new MessageFormat(format);
	}
	
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	public String getTimeFormat() {
		return timeFormat;
	}
	
	public Object[] parse(String source){
		try{
			return messageFormat.parse(source);
		} catch (ParseException e) {
			System.err.println("Error : フォーマットに従っていません。\n" +
					"フォーマット : " + format + "\n" +
					"メッセージ : " + source);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String get(String logRecord, LogItemType type) {
		try {
			message = messageFormat.parse(logRecord);
			return (String) message[getIndex(type)];
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private int getIndex(LogItemType type) {
		for(int i = 0; i < types.size(); i++) {
			if(types.equals(type)) return i;
		}
		
		return -1;
	}
}

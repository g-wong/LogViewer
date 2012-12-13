package jp.co.geo.logviewer.model;

import java.util.HashMap;

abstract class LogFormat {
	
	private String format;
	private String timeFormat;
	
	LogFormat(String format, String timeFormat, HashMap map) {
		this.format = format;
		this.timeFormat = timeFormat;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String getTimeFormat() {
		return timeFormat;
	}
}

package jp.co.geo.logviewer.model;

import java.util.HashMap;

public class AccessLogFormat extends LogFormat{
	public AccessLogFormat(String format, String timeFormat, HashMap map) {
		super(format, timeFormat, map);
	}
	
	public String getHttpStatusCode(String target) {
		return null;
	}
}

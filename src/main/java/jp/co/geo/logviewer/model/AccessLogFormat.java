package jp.co.geo.logviewer.model;

import java.util.ArrayList;

public class AccessLogFormat extends LogFormat{
	public AccessLogFormat(String format, String timeFormat, ArrayList<LogItemType> types) {
		super(format, timeFormat, types);
	}
	
	public String getHttpStatusCode(String logRecord) {
		return get(logRecord, LogItemType.STATUS);
	}
	
	public String getTime(String logRecord) {
		return get(logRecord, LogItemType.TIME);
	}
	
	public static AccessLogFormat getDefaultAccessLogFormat() {
		String format = "";
		String timeFormat = "";
		ArrayList<LogItemType> types = new ArrayList<LogItemType>();
		types.add(LogItemType.TIME);
		types.add(LogItemType.URL);
		types.add(LogItemType.STATUS);
		types.add(LogItemType.PROCESSING_TIME);
		
		return new AccessLogFormat(format, timeFormat, types);
	}
	
	public ArrayList<LogItemType> getTypes() {
		return types;
	}
}

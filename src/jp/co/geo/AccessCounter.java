package jp.co.geo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;

public class AccessCounter {
	Logs logs;
	private Integer cnt[];
	private int range = 10;
	private String unit = "second";
	
	
	public AccessCounter(Logs logs) {
		this.logs = logs;
		long max = logs.getMaxDate().getTime();
		long min = logs.getMinDate().getTime();
		int dataNum = (int) ((max - min) / ( range * parseUnitMillisec(unit)));
		cnt = new Integer[dataNum + 1];
		for (int i = 0; i < cnt.length; i++) {
			cnt[i] = 0;
		}
	}
	
	public Integer[] getCount() {
		long start = logs.getMinDate().getTime();
		Iterator<LogModel> it = logs.iterator();
		while(it.hasNext()) {
			LogModel log = it.next();
			long time = log.getDate().getTime();
			int index = (int) ((time - start) / ( range * parseUnitMillisec(unit)));
			cnt[index]++;
		}
		return cnt;
	}
	
	public String getTime(int index) {
		long start = logs.getMinDate().getTime();
		long time = start + index * (range * parseUnitMillisec(unit));
		Date date = new Date(time);
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(date);
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	private long parseUnitMillisec(String unit) {
		if (unit.equals("s")
				|| unit.equals("second")) {
			return 1000;
		} else if (unit.equals("m")
				|| unit.equals("minute")) {
			return 60 * 1000;
		} else if (unit.equals("h")
				|| unit.equals("hour")) {
			return 3600 * 1000;
		} else if (unit.equals("d")
				|| unit.equals("day")
				|| unit.equals("date")) {
			return 3600 * 1000 * 24;
		}
		
		return 60000;
	}
}

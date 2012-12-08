package jp.co.geo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;

public class AccessCounter {
	Logs logs = null;
	private long maxTime = 0;
	private long minTime = 0;
	private Integer cnt[] = null;
	private int range = 10;
	private String unit = "second";
	private DateFormat df = null;
	
	
	public AccessCounter(Logs logs) {
		this.logs = logs;
		maxTime = logs.getMaxDate().getTime();
		minTime = logs.getMinDate().getTime();
		
	}
	
	private void init() {
		int dataNum = (int) ((maxTime - minTime) / ( range * parseUnitMillisec(unit)));
		System.out.println("dataNum : " + dataNum);
		cnt = new Integer[dataNum + 1];
		for (int i = 0; i < cnt.length; i++) {
			cnt[i] = 0;
		}
	}
	
	public Integer[] getCount() {
		init();
		Iterator<LogModel> it = logs.iterator();
		while(it.hasNext()) {
			LogModel log = it.next();
			long time = log.getDate().getTime();
			int index = (int) ((long)(time - minTime) / ( (long) range * parseUnitMillisec(unit)));
			System.out.println("time - start : " + (time - minTime));
			System.out.println("index : " + index);
			cnt[index]++;
		}
		return cnt;
	}
	
	public String getTime(int index) {
		long start = logs.getMinDate().getTime();
		long time = start + index * (range * parseUnitMillisec(unit));
		Date date = new Date(time);
		if (df == null){
			setFormat("HH:mm");
		}
		return df.format(date);
	}
	
	public void setFormat(String format) {
		this.df = new SimpleDateFormat(format);
		
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

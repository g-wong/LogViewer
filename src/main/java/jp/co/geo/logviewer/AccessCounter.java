package jp.co.geo.logviewer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import jp.co.geo.logviewer.model.LogModel;
import jp.co.geo.logviewer.model.Logs;

public class AccessCounter {
	Logs logs = null;
	private long maxTime = 0;
	private long minTime = 0;
	protected Integer cnt[] = null;
	protected int range = 10;
	protected Unit unit = Unit.SECOND;
	private DateFormat df = null;
	
	enum Unit{
		MILLISEC("millisec"),
		SECOND("second"),
		MINUTE("minute"),
		HOUR("hour"),
		DAY("day"),
		YEAR("year");
		
		private String str;
		
		Unit(String str) {
			this.str = str;
		}
		
		public String toString() {
			return str;
		}
	}
	
	
	public AccessCounter(Logs logs) {
		this.logs = logs;
		maxTime = logs.getMaxDate().getTime();
		minTime = logs.getMinDate().getTime();
		
	}
	
	private void init() {
		int dataNum = (int) ((maxTime - minTime) / ( range * parseUnitMillisec(unit.toString())));
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
			plusCount(log.getDate());
		}
		return cnt;
	}
	
	public String getTime(int index) {
		long start = logs.getMinDate().getTime();
		long time = start + index * (range * parseUnitMillisec(unit.toString()));
		Date date = new Date(time);
		if (df == null){
			setFormat("HH:mm");
		}
		return df.format(date);
	}
	
	private void setFormat(String format) {
		this.df = new SimpleDateFormat(format);
		
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	protected long parseUnitMillisec(String unit) {
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
	
	/**
	 * 日時に該当する時間帯のカウントを１増やす
	 * @param date
	 */
	protected void plusCount(Date date) {
		plusCount(date.getTime());
	}
	
	protected void plusCount(long time) {
		if (time < logs.getMinDate().getTime()
				|| logs.getMaxDate().getTime() < time) {
			return;
		}
		
		int index = (int) ((long)(time - minTime) / ( (long) range * parseUnitMillisec(unit.toString())));
		cnt[index]++;
	}
}

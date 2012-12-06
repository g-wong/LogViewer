package jp.co.geo;

import java.util.Date;
import java.util.Iterator;

import jp.co.geo.model.LogIterator;
import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;

public class AccessCounter {
	Logs logs;
	private int cnt = 0;
	
	public AccessCounter(Logs logs) {
		this.logs = logs;
	}
	
	// TODO 
	public int getCount(Date before, Date after) {
		cnt = 0;
		Iterator<LogModel> it = new LogIterator(logs);
		while (it.hasNext()) {
			LogModel log = it.next();
			Date date = log.getDate();
			if (before.getTime() < date.getTime() 
					&& date.getTime() < after.getTime()){
				cnt++;
			}
		}
		
		return cnt;
	}
}

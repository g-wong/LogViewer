package jp.co.geo;

import java.util.Date;
import java.util.Iterator;

import jp.co.geo.model.LogIterator;
import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;

public class AccessCounter {
	Logs logs;
	
	public AccessCounter(Logs logs) {
		this.logs = logs;
	}
	
	// TODO 
	public int getCount(Date from, Date to) {
		Iterator<LogModel> it = new LogIterator(logs);
		while (it.hasNext()) {
			LogModel log = it.next();
		}
		
		return -1;
	}
}

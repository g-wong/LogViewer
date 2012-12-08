package jp.co.geo.logviewer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class Logs implements Aggregate{
	private ArrayList<LogModel> logsList = new ArrayList<LogModel>();
	
	public Logs() {
	}
	
	public Date getMinDate() {
		Date minDate = new Date(Long.MAX_VALUE);
		
		Iterator<LogModel> it = iterator();
		while(it.hasNext()) {
			LogModel log = it.next();
			if (log.getDate().getTime() < minDate.getTime()) {
				minDate = log.getDate();
			}
		}
		
		return minDate;
	}
	
	public Date getMaxDate() {
		Date maxDate = new Date(0);
		
		Iterator<LogModel> it = iterator();
		while(it.hasNext()) {
			LogModel log = it.next();
			if (log.getDate().getTime() > maxDate.getTime()) {
				maxDate = log.getDate();
			}
		}
		
		return maxDate;
	}
	
	public LogModel getLogAt(int index) {
		if (logsList.size() < index) {
			return null;
		}
		
		return logsList.get(index);
	}
	
	public void appendLog(LogModel log) {
		logsList.add(log);
	}
	
	public int getLength() {
		return logsList.size();
	}

	public Iterator<LogModel> iterator() {
		return new LogIterator(this);
	}

}

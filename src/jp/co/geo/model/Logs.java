package jp.co.geo.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Logs implements Aggregate{
	private ArrayList<LogModel> logsList = new ArrayList<LogModel>();
	
	public Logs() {
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

package jp.co.geo.logviewer.model;

import java.util.Iterator;


public class LogIterator implements Iterator<LogModel> {
	private Logs logs;
	private int index;
	
	public LogIterator(Logs logs) {
		this.logs = logs;
		this.index = 0;
	}

	public boolean hasNext() {
		if (index < logs.getLength()) {
			return true;
		}
		else {
			return false;
		}
	}

	public LogModel next() {
		LogModel log = logs.getLogAt(index);
		index++;
		return log;
	}

	public void remove() {
		
	}

}

package jp.co.geo.model;

import java.util.Iterator;


public class LogIterator implements Iterator<LogModel> {
	private Logs logs;
	private int index;
	
	public LogIterator(Logs logs) {
		this.logs = logs;
		this.index = 0;
	}

	@Override
	public boolean hasNext() {
		if (index < logs.getLength()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public LogModel next() {
		LogModel log = logs.getLogAt(index);
		index++;
		return log;
	}

	@Override
	public void remove() {
		
	}

}

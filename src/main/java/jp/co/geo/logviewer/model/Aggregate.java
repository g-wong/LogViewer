package jp.co.geo.logviewer.model;

import java.util.Iterator;


public interface Aggregate {
	Iterator<LogModel> iterator();
}

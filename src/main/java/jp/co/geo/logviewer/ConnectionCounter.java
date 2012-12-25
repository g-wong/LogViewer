package jp.co.geo.logviewer;

import java.util.Date;
import java.util.Iterator;

import jp.co.geo.logviewer.model.LogModel;
import jp.co.geo.logviewer.model.Logs;

/**
 * 各時間ごとのコネクション数
 * setRange() で時間幅を決めて、setUnit() でその時間単位を決める
 * その後 getCount() でコネクション数を取得する
 * @author Administrator
 *
 */
public class ConnectionCounter extends AccessCounter {

	public ConnectionCounter(Logs logs) {
		super(logs);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * コネクション数を配列で返却
	 */
	@Override
	public Integer[] getCount() {
		Iterator<LogModel> it = logs.iterator();
		while(it.hasNext()) {
			LogModel log = it.next();
			if (log.getProcessingTime() == null) return null;
			
			long processingTime = Long.parseLong(log.getProcessingTime());
			Date date = log.getDate();
			
			long start = date.getTime() - processingTime;
			long time = 0;
			while (start + time < date.getTime() + ( (long) range * parseUnitMillisec(unit.toString())) ) {
				plusCount(start + time);
				time += (long) range * parseUnitMillisec(unit.toString());
			}
		}
		
		return cnt;
	}

}

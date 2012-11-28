package jp.co.geo;

import java.text.MessageFormat;
import java.text.ParseException;

public class Analyzer {
	
    MessageFormat format;
	
	public Analyzer() {
        // アクセスログのフォーマット
        format = new MessageFormat("{0} - [{1}] \"GET {2} {3}\" {4} {5}");
	}
	
	
	public Object[] analyze(StringBuffer buffer) {
		return analyze(buffer.toString());
	}
	
	public Object[] analyze(String str) {
		Object[] message;
		try {
			message = format.parse(str);
			return message;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

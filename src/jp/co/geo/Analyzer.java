package jp.co.geo;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class Analyzer
{
	private Display display;
	private ArrayList<StringBuffer> bufferList;
	private ArrayList<StyledText> textList;
	
	public Analyzer(Display display,
			ArrayList<StringBuffer> bufferList, 
			ArrayList<StyledText> textList) {
		this.display = display;
		this.bufferList = bufferList;
		this.textList = textList;
	}
	
	public void run() {
		if(bufferList.isEmpty()) return;
		
		for (int i = 0; i < textList.size(); i++) {
			if (bufferList.get(i) == null) break;
			setTextStyle(textList.get(i));
			textList.get(i).setText(bufferList.get(i).toString());
		}
	}
	
	private  void setTextStyle(StyledText styledText){
		// error•¶Žš—ñ‚ðÔ•¶Žš‚É‚·‚é
		Color red = display.getSystemColor(SWT.COLOR_RED);
		styledText.addLineStyleListener(
				new HilightListener(red, "error"));
	}
	
	
}

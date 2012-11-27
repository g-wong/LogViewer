package jp.co.geo;

import java.util.ArrayList;

import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

public class HilightListener implements LineStyleListener {
	
	private Color color;
	private String str;
	
	public HilightListener(Color color, String str) {
		this.color = color;
		this.str = str;
	}
	
	public void lineGetStyle(LineStyleEvent event) {
		String text = event.lineText;
		int lastIndex = 0;
		int index = 0;
		ArrayList<StyleRange> styles = new ArrayList<StyleRange>();
		while((index = text.indexOf(str, lastIndex)) != -1) {
			StyleRange range = new StyleRange();
			range.start = event.lineOffset + index;
			range.length = 5;
			range.foreground = color;
			styles.add(range);
			lastIndex = index + 5;
		}
		event.styles = (StyleRange[])styles.toArray(
				new StyleRange[styles.size()]);
	}

}

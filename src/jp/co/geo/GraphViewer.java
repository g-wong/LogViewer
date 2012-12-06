package jp.co.geo;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JFrame;

import jp.co.geo.model.LogModel;
import jp.co.geo.model.Logs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphViewer extends JFrame{
	
	private DefaultCategoryDataset data = new DefaultCategoryDataset();;

	public GraphViewer(Logs logs){
		setData(logs);
	}
	
	public void setData(Logs logs) {
		AccessCounter counter = new AccessCounter(logs);
		counter.setRange(5);
		counter.setUnit("minute");
		Integer cnt[] = counter.getCount();
		System.out.println(cnt.length);
		for (int i = 0; i < cnt.length; i++) {
			data.addValue(cnt[i], "Count", counter.getTime(i));
			
		}
	}
	
	public void plot() {
		JFreeChart chart = 
			      ChartFactory.createLineChart("count",
			                                   "Time",
			                                   "minute",
			                                   data,
			                                   PlotOrientation.VERTICAL,
			                                   true,
			                                   false,
			                                   false);
		ChartPanel cpanel = new ChartPanel(chart);
	    getContentPane().add(cpanel, BorderLayout.CENTER);
	    this.setVisible(true);
	}
}


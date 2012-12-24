package jp.co.geo.logviewer;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import jp.co.geo.logviewer.model.Logs;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphViewer extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset data = new DefaultCategoryDataset();;

	public GraphViewer(Logs logs){
		setData(logs);
	}
	
	public void setData(Logs logs) {
		AccessCounter counter = new AccessCounter(logs);
		counter.setRange(10);
		counter.setUnit("second");
		Integer cnt[] = counter.getCount();
		for (int i = 0; i < cnt.length; i++) {
			data.addValue(cnt[i], "Count", counter.getTime(i));
			
		}
	}
	
	public void plot() {
		JFreeChart chart = 
			      ChartFactory.createLineChart("Access Count",
			                                   "Time",
			                                   "count",
			                                   data,
			                                   PlotOrientation.VERTICAL,
			                                   true,
			                                   false,
			                                   false);
		setChartForm(chart);
		ChartPanel cpanel = new ChartPanel(chart);
	    getContentPane().add(cpanel, BorderLayout.CENTER);
	    this.setVisible(true);
	}
	
	private void setChartForm(JFreeChart chart) {
		final CategoryPlot plot = chart.getCategoryPlot();
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	}
}


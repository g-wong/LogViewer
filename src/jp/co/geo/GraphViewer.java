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
	
	public static void main(String[] args) {
		GraphViewer frame = new GraphViewer();
		frame.plot();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 500, 500);
		frame.setTitle("グラフサンプル");
		frame.setVisible(true);
	}

	public GraphViewer(){
	    String[] series = {"米国", "中国", "インド"};
	    String[] category = {"2005年", "2006年", "2007年"};

	    data.addValue(300, series[0], category[0]);
	    data.addValue(500, series[0], category[1]);
	    data.addValue(400, series[0], category[2]);
	    data.addValue(200, series[1], category[0]);
	    data.addValue(600, series[1], category[1]);
	    data.addValue(200, series[1], category[2]);
	    data.addValue(100, series[2], category[0]);
	    data.addValue(150, series[2], category[1]);
	    data.addValue(700, series[2], category[2]);

	    
		
	}
	
	public void setData(Logs logs) {
		Iterator it = logs.iterator();
		
		while (it.hasNext()) {
			LogModel log = (LogModel) it.next();
			log.getDate();
		}
	}
	
	public void plot() {
		JFreeChart chart = 
			      ChartFactory.createLineChart("輸入量",
			                                   "年度",
			                                   "トン(t)",
			                                   data,
			                                   PlotOrientation.VERTICAL,
			                                   true,
			                                   false,
			                                   false);
				ChartPanel cpanel = new ChartPanel(chart);
			    getContentPane().add(cpanel, BorderLayout.CENTER);
	}
}


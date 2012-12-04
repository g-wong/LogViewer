package jp.co.geo;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraphViewer extends JFrame{
	public static void main(String[] args) {
		GraphViewer frame = new GraphViewer();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 500, 500);
		frame.setTitle("グラフサンプル");
		frame.setVisible(true);
	}

	public GraphViewer(){
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		JFreeChart chart = 
				ChartFactory.createLineChart("輸入量",
						"年度",
						"トン(t)",
						data,
						PlotOrientation.HORIZONTAL,
						true,
						true,
						false);

		ChartPanel cpanel = new ChartPanel(chart);
	    getContentPane().add(cpanel, BorderLayout.CENTER);
		
	}
}


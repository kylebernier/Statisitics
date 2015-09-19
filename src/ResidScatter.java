import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// TODO: Auto-generated Javadoc
/**
 * The Class ResidScatter.
 */
public class ResidScatter extends JFrame {

	/** The b. */
	double r, r2, a, b;

	/** The L2. */
	String L1, L2;

	/** The lists. */
	float[][] lists;

	/** The resid. */
	float[] resid;

	/** The predicted. */
	float[] predicted;

	/**
	 * Instantiates a new resid scatter.
	 *
	 * @param L1
	 *            the l1
	 * @param L2
	 *            the l2
	 * @param lists
	 *            the lists
	 */
	public ResidScatter(String L1, String L2, float[][] lists) {
		super("Residual Data Scatter");
		this.L1 = L1;
		this.L2 = L2;
		this.lists = lists;
		r = Calculations.getR(lists);
		r2 = Calculations.getR2(lists);
		a = Calculations.getA(lists);
		b = Calculations.getB(lists);
		final ChartPanel chartPanel = createDemoPanel();
		getContentPane().add(chartPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the demo panel.
	 *
	 * @return the chart panel
	 */
	private ChartPanel createDemoPanel() {
		XYDataset dataSet = createData();
		NumberAxis numberaxis = new NumberAxis("Predicted " + L2);
		numberaxis.setAutoRangeIncludesZero(false);
		NumberAxis numberaxis1 = new NumberAxis("Residual");
		numberaxis1.setAutoRangeIncludesZero(false);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(false, true);
		XYPlot xyplot = new XYPlot(dataSet, numberaxis, numberaxis1, xylineandshaperenderer);
		double ad[] = Regression.getOLSRegression(dataSet, 0);
		LineFunction2D linefunction2d = new LineFunction2D(ad[0], ad[1]);
		XYDataset xydataset = DatasetUtilities.sampleFunction2D(linefunction2d, Calculations.getMin(predicted), Calculations.getMax(predicted), 100, "Actual Values");
		xyplot.setDataset(1, xydataset);
		XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer(true, false);
		xylineandshaperenderer1.setSeriesPaint(0, Color.blue);
		xyplot.setRenderer(1, xylineandshaperenderer1);
		String header = "r: " + r + "\nr\u00B2: " + r2 + "\n" + L2 + "=" + a + "+" + b + "(" + L1 + ")";
		JFreeChart jfreechart = new JFreeChart(header, JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
		ChartPanel chartpanel = new ChartPanel(jfreechart, false);
		return chartpanel;
	}

	/**
	 * Creates the data.
	 *
	 * @return the XY dataset
	 */
	private XYDataset createData() {
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
		XYSeries series = new XYSeries("Data Value");

		float[][] values = Calculations.getResid(lists);
		resid = values[0];
		predicted = values[1];

		for (int i = 0; i < resid.length; i++) {
			series.add(predicted[i], resid[i]);
		}
		xySeriesCollection.addSeries(series);
		return xySeriesCollection;
	}
}
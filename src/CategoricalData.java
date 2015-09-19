import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoricalData.
 */
public class CategoricalData extends JInternalFrame {

	/**
	 * Instantiates a new categorical data.
	 */
	public CategoricalData() {
		setup();
	}

	/**
	 * Setup.
	 */
	private void setup() {
		setTitle("Categorical Data");
		setClosable(true);
		setBounds(322, 12, 298, 227);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Graphs", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel.setBounds(141, 12, 139, 170);
		getContentPane().add(panel);

		JButton btnPieChart = new JButton("Pie Chart");
		btnPieChart.setBounds(12, 133, 117, 25);
		panel.add(btnPieChart);

		JButton btnBoxPlot = new JButton("Box Plot");
		btnBoxPlot.setBounds(12, 22, 117, 25);
		panel.add(btnBoxPlot);

		JButton btnHistogram = new JButton("Histogram");
		btnHistogram.setBounds(12, 59, 117, 25);
		panel.add(btnHistogram);

		JButton btnBarChart = new JButton("Bar Chart");
		btnBarChart.setBounds(12, 96, 117, 25);
		panel.add(btnBarChart);

		JButton button = new JButton("Data");
		button.setBounds(12, 12, 117, 25);
		getContentPane().add(button);

		JButton button_1 = new JButton("Results");
		button_1.setBounds(12, 49, 117, 25);
		getContentPane().add(button_1);
		setVisible(true);
	}
}
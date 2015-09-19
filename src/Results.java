import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Results.
 */
public class Results extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The table. */
	JTable table;

	/** The model. */
	final DefaultTableModel model;

	/** The data. */
	static float[][] data;

	/** The list1. */
	float[] list1;

	/** The list2. */
	float[] list2;

	/**
	 * Create the frame.
	 *
	 * @param lists
	 *            the lists
	 * @param L1
	 *            the l1
	 * @param L2
	 *            the l2
	 */
	public Results(float[][] lists, String L1, String L2) {
		data = lists;

		list1 = data[0];
		list2 = data[1];

		setBounds(100, 100, 240, 302);
		setTitle("Results");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);

		// Create a couple of columns
		model.addColumn("Var");
		model.addColumn(L1);
		model.addColumn(L2);

		model.addRow(new Object[] { "Mean", Calculations.getMean(list1), Calculations.getMean(list2) });
		model.addRow(new Object[] { "\u03A3", Calculations.getSum(list1), Calculations.getSum(list2) });
		model.addRow(new Object[] { "\u03A3\u00B2", Calculations.getSum2(list1), Calculations.getSum2(list2) });
		model.addRow(new Object[] { "S", Calculations.getSSD(list1), Calculations.getSSD(list2) });
		model.addRow(new Object[] { "\u03C3", Calculations.getSD(list1), Calculations.getSD(list2) });
		model.addRow(new Object[] { "n", list1.length, list2.length });
		model.addRow(new Object[] { "Min", Calculations.getMin(list1), Calculations.getMin(list2) });
		model.addRow(new Object[] { "Q1", Calculations.getQ1(list1), Calculations.getQ1(list2) });
		model.addRow(new Object[] { "Med", Calculations.getMed(list1), Calculations.getMed(list2) });
		model.addRow(new Object[] { "Q3", Calculations.getQ3(list1), Calculations.getQ3(list2) });
		model.addRow(new Object[] { "Max", Calculations.getMax(list1), Calculations.getMax(list2) });

		getContentPane().add(new JScrollPane(table));
	}
}

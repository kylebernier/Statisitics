import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class ScatterData.
 */
public class ScatterData extends JInternalFrame {

	/** The data. */
	static Data data = new Data();

	/** The lists. */
	static float[][] lists;

	/** The Lin scatter. */
	private LinScatterData LinScatter;

	/**
	 * Instantiates a new scatter data.
	 */
	public ScatterData() {
		setup();
	}

	/**
	 * Setup.
	 */
	private void setup() {
		setTitle("Scatter Data");
		setClosable(true);
		setBounds(12, 12, 298, 264);
		getContentPane().setLayout(null);
		data.addTen();

		JButton viewData = new JButton("Data");
		viewData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data.setVisible(true);
			}
		});
		viewData.setBounds(12, 12, 117, 25);
		getContentPane().add(viewData);

		JButton viewResults = new JButton("Results");
		viewResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();

				Results results = new Results(lists, data.getColumnName(0), data.getColumnName(1));
				results.setVisible(true);
			}
		});
		viewResults.setBounds(12, 49, 117, 25);
		getContentPane().add(viewResults);

		JButton viewGraph = new JButton("Graph");
		viewGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				RegScatter f = new RegScatter(data.getColumnName(0), data.getColumnName(1), lists);
				f.setSize(1000, 600);
				f.setVisible(true);
			}
		});
		viewGraph.setBounds(12, 86, 117, 25);
		getContentPane().add(viewGraph);

		JButton viewResid = new JButton("Residuals");
		viewResid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				Data resid = new Data();
				String[] names = { "Residual", "Predicted " + data.getColumnName(1) };
				resid.setTableData(Calculations.getResid(lists), names);
				resid.setVisible(true);
			}
		});
		viewResid.setBounds(12, 123, 117, 25);
		getContentPane().add(viewResid);

		JButton viewResidGraph = new JButton("Resid Graph");
		viewResidGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				ResidScatter f = new ResidScatter(data.getColumnName(0), data.getColumnName(1), lists);
				f.setSize(1000, 600);
				f.setVisible(true);
			}
		});
		viewResidGraph.setBounds(12, 160, 117, 25);
		getContentPane().add(viewResidGraph);

		JButton linearizeBtn = new JButton("Linearize");
		linearizeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				LinScatter.linearize(lists);
			}
		});
		linearizeBtn.setBounds(12, 197, 117, 25);
		getContentPane().add(linearizeBtn);

		LinScatter = new LinScatterData();
		getContentPane().add(LinScatter);
	}
}
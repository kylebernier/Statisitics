import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class LinScatterData.
 */
public class LinScatterData extends JPanel {

	/** The data. */
	static Data data = new Data();

	/** The lists. */
	static float[][] lists;

	/** The view linear resid graph. */
	private JButton viewLinearData, viewLinearResults, viewLinearGraph, viewLinearResid, viewLinearResidGraph;

	/**
	 * Instantiates a new lin scatter data.
	 */
	public LinScatterData() {
		setup();
	}

	/**
	 * Setup.
	 */
	private void setup() {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Linerized", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		setBounds(141, 12, 139, 207);
		setLayout(null);

		viewLinearData = new JButton("Data");
		viewLinearData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				data.setVisible(true);
			}
		});
		viewLinearData.setBounds(12, 22, 117, 25);
		add(viewLinearData);
		viewLinearData.setEnabled(false);

		viewLinearResults = new JButton("Results");
		viewLinearResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();

				Results results = new Results(lists, data.getColumnName(0), data.getColumnName(1));
				results.setVisible(true);
			}
		});
		viewLinearResults.setBounds(12, 59, 117, 25);
		add(viewLinearResults);
		viewLinearResults.setEnabled(false);

		viewLinearGraph = new JButton("Graph");
		viewLinearGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				RegScatter f = new RegScatter(data.getColumnName(0), data.getColumnName(1), lists);
				f.setSize(1000, 600);
				f.setVisible(true);
			}
		});
		viewLinearGraph.setBounds(12, 96, 117, 25);
		add(viewLinearGraph);
		viewLinearGraph.setEnabled(false);

		viewLinearResid = new JButton("Residuals");
		viewLinearResid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				Data resid = new Data();
				String[] names = { "Residual", "Predicted " + data.getColumnName(1) };
				resid.setTableData(Calculations.getResid(lists), names);
				resid.setVisible(true);
			}
		});
		viewLinearResid.setBounds(12, 133, 117, 25);
		add(viewLinearResid);
		viewLinearResid.setEnabled(false);

		viewLinearResidGraph = new JButton("Resid Graph");
		viewLinearResidGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lists = data.getTableData();
				ResidScatter f = new ResidScatter(data.getColumnName(0), data.getColumnName(1), lists);
				f.setSize(1000, 600);
				f.setVisible(true);
			}
		});
		viewLinearResidGraph.setBounds(12, 170, 117, 25);
		add(viewLinearResidGraph);
		viewLinearResidGraph.setEnabled(false);
	}

	/**
	 * Linearize.
	 *
	 * @param list
	 *            the list
	 */
	public void linearize(float[][] list) {
		data = new Data();
		lists = null;

		int length = list[0].length;

		double a, b, c, d, e, f, g, h, i, j, k, l, m, n;

		float[] ax = new float[length];
		float[] ay = new float[length];
		float[] bx = new float[length];
		float[] by = new float[length];
		float[] cx = new float[length];
		float[] cy = new float[length];
		float[] dx = new float[length];
		float[] dy = new float[length];
		float[] ex = new float[length];
		float[] ey = new float[length];
		float[] fx = new float[length];
		float[] fy = new float[length];

		// Unchanged
		for (int z = 0; z < length; z++)
			ax[z] = list[0][z];
		for (int z = 0; z < length; z++)
			ay[z] = list[1][z];

		// Squared
		for (int z = 0; z < length; z++)
			bx[z] = (float) Math.pow(list[0][z], 2);
		for (int z = 0; z < length; z++)
			by[z] = (float) Math.pow(list[1][z], 2);

		// Square Root
		for (int z = 0; z < length; z++)
			cx[z] = (float) Math.sqrt(list[0][z]);
		for (int z = 0; z < length; z++)
			cy[z] = (float) Math.sqrt(list[1][z]);

		// Natural Log
		for (int z = 0; z < length; z++)
			dx[z] = (float) Math.log1p(list[0][z]);
		for (int z = 0; z < length; z++)
			dy[z] = (float) Math.log1p(list[1][z]);

		// Inverse Square Root
		for (int z = 0; z < length; z++)
			ex[z] = (float) (-1 / Math.sqrt(list[0][z]));
		for (int z = 0; z < length; z++)
			ey[z] = (float) (-1 / Math.sqrt(list[1][z]));

		// Inverse
		for (int z = 0; z < length; z++)
			fx[z] = -1 / list[0][z];
		for (int z = 0; z < length; z++)
			fy[z] = -1 / list[1][z];

		float[][] a1 = { ax, ay };
		float[][] b1 = { ax, by };
		float[][] c1 = { bx, ay };
		float[][] d1 = { ax, cy };
		float[][] e1 = { cx, ay };
		float[][] f1 = { ax, dy };
		float[][] g1 = { dx, ay };
		float[][] h1 = { dx, dy };
		float[][] i1 = { ax, ey };
		float[][] j1 = { ex, ay };
		float[][] k1 = { ex, ey };
		float[][] l1 = { ax, fy };
		float[][] m1 = { fx, ay };
		float[][] n1 = { fx, fy };
		float[][][] z1 = { a1, b1, c1, d1, e1, f1, g1, h1, i1, j1, k1, l1, m1, n1 };

		float[] rs = new float[14];
		a = Calculations.getR(a1);
		rs[0] = (float) a;
		b = Calculations.getR(b1);
		rs[1] = (float) b;
		c = Calculations.getR(c1);
		rs[2] = (float) c;
		d = Calculations.getR(d1);
		rs[3] = (float) d;
		e = Calculations.getR(e1);
		rs[4] = (float) e;
		f = Calculations.getR(f1);
		rs[5] = (float) f;
		g = Calculations.getR(g1);
		rs[6] = (float) g;
		h = Calculations.getR(h1);
		rs[7] = (float) h;
		i = Calculations.getR(i1);
		rs[8] = (float) i;
		j = Calculations.getR(j1);
		rs[9] = (float) j;
		k = Calculations.getR(k1);
		rs[10] = (float) k;
		l = Calculations.getR(l1);
		rs[11] = (float) l;
		m = Calculations.getR(m1);
		rs[12] = (float) m;
		n = Calculations.getR(n1);
		rs[13] = (float) n;

		for (int z = 0; z < rs.length; z++) {
			rs[z] = Math.abs(rs[z]);
		}

		double max = Calculations.getMax(rs);
		int num = 0;
		for (int z = rs.length - 1; z > -1; z--) {
			if (rs[z] == max) {
				num = z;
				continue;
			}
		}

		lists = z1[num];

		Main.getScatterData();
		String xCast = ScatterData.data.getColumnName(1);
		Main.getScatterData();
		String yCast = ScatterData.data.getColumnName(0);

		if (num == 1) {
			xCast = xCast + "\u00B2";
		}
		if (num == 2) {
			yCast = yCast + "\u00B2";
		}
		if (num == 3) {
			xCast = "\u221A(" + xCast + ")";
		}
		if (num == 4) {
			yCast = "\u221A(" + yCast + ")";
		}
		if (num == 5) {
			xCast = "Ln(" + xCast + ")";
		}
		if (num == 6) {
			yCast = "Ln(" + yCast + ")";
		}
		if (num == 7) {
			xCast = "Ln(" + xCast + ")";
			yCast = "Ln(" + yCast + ")";
		}
		if (num == 8) {
			xCast = "-1\u221A(" + xCast + ")";
		}
		if (num == 9) {
			yCast = "-1\u221A(" + yCast + ")";
		}
		if (num == 10) {
			xCast = "-1\u221A(" + xCast + ")";
			yCast = "-1\u221A(" + yCast + ")";
		}
		if (num == 11) {
			xCast = "-1/" + xCast;
		}
		if (num == 12) {
			yCast = "-1/" + yCast;
		}
		if (num == 13) {
			xCast = "-1/" + xCast;
			yCast = "-1/" + yCast;
		}

		String[] names = { yCast, xCast };

		data.setTableData(lists, names);

		enableBtn();
	}

	/**
	 * Enable btn.
	 */
	private void enableBtn() {
		viewLinearData.setEnabled(true);
		viewLinearResults.setEnabled(true);
		viewLinearGraph.setEnabled(true);
		viewLinearResid.setEnabled(true);
		viewLinearResidGraph.setEnabled(true);
	}
}
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

// TODO: Auto-generated Javadoc
/**
 * The Class Utilities.
 */
public class Utilities {

	/** The width. */
	private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;

	/** The height. */
	private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	/**
	 * Save.
	 *
	 * @param lists
	 *            the lists
	 */
	public static void save(float[][] lists) {
		File file = null;

		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(fc);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			File file1 = new File(file.getParent(), file.getName() + ".stats");
			try {
				FileWriter fw = new FileWriter(file1);
				BufferedWriter bw = new BufferedWriter(fw);

				Date date = new Date();

				bw.write("# " + file.getName() + " Data Set");
				bw.newLine();
				bw.write("# Last Modified " + date.toString());
				bw.newLine();
				bw.write("%" + lists.length + " " + lists[0].length);
				bw.newLine();
				bw.write("&");
				for (int j = 0; j < lists.length; j++) {
					bw.write(ScatterData.data.getColumnName(j) + " ");
				}
				bw.newLine();

				for (int i = 0; i < lists[0].length; i++) {
					for (int j = 0; j < lists.length; j++) {
						bw.write(lists[j][i] + " ");
					}
					bw.newLine();
				}

				bw.close();

				JOptionPane.showMessageDialog(null, "Saved.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Load.
	 */
	public static void load() {
		final JFrame op = new JFrame("New");
		JPanel pane = new JPanel();

		final JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Data, STATS ", "stats");
		fc.addChoosableFileFilter(filter);
		fc.setControlButtonsAreShown(false);
		JButton load = new JButton("Open");

		pane.add(fc);
		pane.add(load);
		op.add(pane);
		op.setResizable(false);
		op.setMinimumSize(new Dimension(520, 370 + 30)); // TITLE_HEIGHT
		op.setLocation(width / 2 - 280, height / 2 - 185);
		op.setVisible(true);

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = fc.getSelectedFile();

				FileReader fr;
				try {
					fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);

					String row;

					int columns = 2, rows = 10, c = 0;

					String[] names = null;

					float[][] list = null;

					float value;

					while ((row = br.readLine()) != null) {
						if (!row.startsWith("#")) {
							if (row.startsWith("%")) {
								columns = Integer.parseInt(row.substring(1, row.indexOf(" ")));
								rows = Integer.parseInt(row.substring(row.indexOf(" ") + 1));
								list = new float[columns][rows];
							} else {
								if (row.startsWith("&")) {
									names = new String[columns];
									int a = row.indexOf(" ");
									int b = 1;
									for (int i = 0; i < columns; i++) {
										names[i] = row.substring(b, a);
										b = row.indexOf(" ", b) + 1;
										a = row.indexOf(" ", b);
									}
								} else {
									int a = row.indexOf(" ");
									int b = 0;

									for (int i = 0; i < columns; i++) {
										value = Float.parseFloat(row.substring(b, a));
										list[i][c] = value;
										b = row.indexOf(" ", b) + 1;
										a = row.indexOf(" ", b);
									}
									c++;
								}
							}
						}
					}

					br.close();

					ScatterData.data = new Data();
					ScatterData.data.setTableData(list, names);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				op.dispose();
			}
		});
	}
}
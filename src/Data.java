import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class Data.
 */
public class Data extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The table. */
	JTable table;

	/** The model. */
	DefaultTableModel model;

	/** The btn remove row. */
	private JButton btnRemoveRow;

	/** The btn add column. */
	private JButton btnAddColumn;

	/** The btn remove column. */
	private JButton btnRemoveColumn;

	/** The row header. */
	private JList rowHeader;

	/** The lm. */
	RowModel lm;

	/**
	 * Instantiates a new data.
	 */
	public Data() {
		setBounds(100, 100, 320, 348);
		setTitle("Data");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		model = new DefaultTableModel();
		contentPane.setLayout(new MigLayout("", "[137px:137px,grow,trailing][137px:137px,grow,leading]", "[249px,grow][25px][25px]"));
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(0);

		final JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll, "cell 0 0 2 1,grow");

		lm = new RowModel();
		rowHeader = new JList(lm);
		rowHeader.setFixedCellWidth(50);
		rowHeader.setFixedCellHeight(table.getRowHeight() + table.getRowMargin() - 1);
		rowHeader.setCellRenderer(new RowHeaderRenderer(table));
		scroll.setRowHeaderView(rowHeader);

		scroll.getViewport().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				rowHeader.repaint();
				rowHeader.revalidate();
			}
		});

		rowHeader.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent m) {
				if (m.getClickCount() == 2) {
					final int row = rowHeader.locationToIndex(m.getPoint());
					final JPopupMenu rename = new JPopupMenu();
					final JTextField name = new JTextField(lm.getElementAt(row) + "");
					JButton submit = new JButton("Rename");
					rename.add(name);
					rename.add(submit);
					rename.show(m.getComponent(), m.getX(), m.getY());

					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							String a = name.getText();
							lm.renameElement(row, a);
							rename.setVisible(false);
						}
					});
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		table.getTableHeader().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent m) {
				if (m.getClickCount() == 2) {
					final int column = table.columnAtPoint(m.getPoint());
					final JPopupMenu rename = new JPopupMenu();
					final JTextField name = new JTextField(model.getColumnName(column));
					JButton submit = new JButton("Rename");
					rename.add(name);
					rename.add(submit);
					rename.show(m.getComponent(), m.getX(), m.getY());

					submit.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							String a = name.getText();
							table.getColumnModel().getColumn(column).setHeaderValue(a);
							scroll.repaint();
							rename.setVisible(false);
						}
					});
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});

		JButton btnNewButton = new JButton("Add Row");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.addRow(new Object[] { null, null });
				lm.addElement();
				rowHeader.repaint();
			}
		});
		contentPane.add(btnNewButton, "cell 0 1,grow");

		btnAddColumn = new JButton("Add Column");
		btnAddColumn.setEnabled(false);
		btnAddColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.addColumn("list" + (model.getColumnCount() + 1));
			}
		});
		contentPane.add(btnAddColumn, "cell 1 1,grow");

		btnRemoveRow = new JButton("Remove Row");
		btnRemoveRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.removeRow(model.getRowCount() - 1);
				lm.removeElement(model.getRowCount());
				rowHeader.repaint();
			}
		});
		contentPane.add(btnRemoveRow, "cell 0 2,grow");

		btnRemoveColumn = new JButton("Remove Column");
		btnRemoveColumn.setEnabled(false);
		btnRemoveColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setColumnCount(model.getColumnCount() - 1);
			}
		});
		contentPane.add(btnRemoveColumn, "cell 1 2,grow");
	}

	/**
	 * Adds the ten.
	 */
	public void addTen() {
		// Create a couple of columns
		model.addColumn("list1");
		model.addColumn("list2");

		for (int i = 0; i < 10; i++) {
			model.addRow(new Object[] { (i + 1), ((i + 1) * (i + 1)) });
			lm.addElement();
		}
	}

	/**
	 * Gets the table data.
	 *
	 * @return the table data
	 */
	public float[][] getTableData() {
		int nRow = model.getRowCount(), nCol = model.getColumnCount();
		float[][] tableData = new float[nCol][nRow];
		for (int i = 0; i < nCol; i++)
			for (int j = 0; j < nRow; j++)
				if (table.getValueAt(j, i) != null) {
					double a = Double.valueOf(table.getValueAt(j, i) + "");
					float b = (float) a;
					tableData[i][j] = b;
				}
		return tableData;
	}

	/**
	 * Sets the table data.
	 *
	 * @param lists
	 *            the lists
	 * @param columns
	 *            the columns
	 */
	public void setTableData(float[][] lists, String[] columns) {
		for (int j = 0; j < columns.length; j++) {
			model.addColumn(columns[j]);
		}

		for (int i = 0; i < lists[0].length; i++) {
			model.addRow(new Object[] { null, null });
			lm.addElement();
			for (int j = 0; j < columns.length; j++) {
				table.setValueAt(lists[j][i], i, j);
			}
		}
	}

	/**
	 * Gets the column name.
	 *
	 * @param i
	 *            the i
	 * @return the column name
	 */
	public String getColumnName(int i) {
		return table.getColumnModel().getColumn(i).getHeaderValue().toString();
	}
}

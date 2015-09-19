import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends JFrame {

	/** The content pane. */
	private JPanel contentPane;

	/** The scatter data. */
	private static ScatterData scatterData;

	/**
	 * Launch the application.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Statistics");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 433);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scatterData = new ScatterData();
		contentPane.add(scatterData);
		scatterData.setVisible(true);

		CategoricalData categoricalData = new CategoricalData();
		contentPane.add(categoricalData);
		categoricalData.setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utilities.save(ScatterData.data.getTableData());
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utilities.load();
			}
		});
		mnFile.add(mntmLoad);

		// JMenu mnView = new JMenu("View");
		// menuBar.add(mnView);
		//
		// JMenuItem mntmScatterData = new JMenuItem("Scatter Data");
		// mnView.add(mntmScatterData);
		//
		// JMenuItem mntmCategoricalData = new JMenuItem("Categorical Data");
		// mnView.add(mntmCategoricalData);
	}

	/**
	 * Gets the scatter data.
	 *
	 * @return the scatter data
	 */
	public static ScatterData getScatterData() {
		return scatterData;
	}
}

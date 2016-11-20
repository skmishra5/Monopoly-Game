package cs414.a5.k.view;

/*
 * SwingApplication.java is a 1.4 example that requires
 * no other files.
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class StartMonopoly implements ActionListener {

	String[] numberOfPlayers = { "1", "2", "3", "4" };

	// Specify the look and feel to use. Valid values:
	// null (use the default), "Metal", "System", "Motif", "GTK+"
	final static String LOOKANDFEEL = null;
	final JLabel label = new JLabel("Choose number of players");

	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox numPlayers = new JComboBox(numberOfPlayers);
	String comboBoxValue = null;

	public Component createComponents() {
		JButton button = new JButton("Start Game");

		button.setMnemonic(KeyEvent.VK_I);
		button.addActionListener(this);

		/*
		 * An easy way to put space between a top-level container and its
		 * contents is to put the contents in a JPanel that has an "empty"
		 * border.
		 */

		JPanel pane = new JPanel(new GridLayout(0, 1));
		pane.add(label);
		pane.add(Box.createVerticalStrut(10));
		pane.add(numPlayers);
		pane.add(Box.createVerticalStrut(30));
		pane.add(button);
		pane.setBorder(BorderFactory.createEmptyBorder(100, // top
				700, // left
				700, // bottom
				700) // right
		);

		return pane;
	}

	public void actionPerformed(ActionEvent e) {
		comboBoxValue = numPlayers.getSelectedItem().toString();
		new MonopolyUI(0, comboBoxValue, 0).setVisible(true);
	}

	private static void initLookAndFeel() {
		String lookAndFeel = null;

		if (LOOKANDFEEL != null) {
			if (LOOKANDFEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			} else if (LOOKANDFEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			} else if (LOOKANDFEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			} else if (LOOKANDFEEL.equals("GTK+")) { // new in 1.4.2
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			} else {
				System.err.println("Unexpected value of LOOKANDFEEL specified: " + LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {
				UIManager.setLookAndFeel(lookAndFeel);
			} catch (ClassNotFoundException e) {
				System.err.println("Couldn't find class for specified look and feel:" + lookAndFeel);
				System.err.println("Did you include the L&F library in the class path?");
				System.err.println("Using the default look and feel.");
			} catch (UnsupportedLookAndFeelException e) {
				System.err.println("Can't use the specified look and feel (" + lookAndFeel + ") on this platform.");
				System.err.println("Using the default look and feel.");
			} catch (Exception e) {
				System.err.println("Couldn't get specified look and feel (" + lookAndFeel + "), for some reason.");
				System.err.println("Using the default look and feel.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Set the look and feel.
		initLookAndFeel();

		// Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Create and set up the window.
		JFrame frame = new JFrame("StartMonopoly");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		StartMonopoly app = new StartMonopoly();
		Component contents = app.createComponents();
		frame.getContentPane().add(contents, BorderLayout.CENTER);

		// Display the window.
		frame.pack();
		frame.setSize(260, 260);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}

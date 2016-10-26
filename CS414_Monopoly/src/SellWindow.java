
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * A Swing program that demonstrates how to create and use JComboBox component.
 * 
 * @author www.codejava.net
 * 
 */
public class SellWindow {

	private JButton buttonSell = new JButton("Sell");
	private JButton buttonMortgage = new JButton("Mortgage");
	
	static PipedOutputStream output = new PipedOutputStream();
	static PipedInputStream  input  = new PipedInputStream();
	static PipedOutputStream mortOutput = new PipedOutputStream();
	static PipedInputStream  mortInput  = new PipedInputStream();

	static String selectedProp = "";
	static String selectedMortSell = "";

    
	public SellWindow(String[] propList) {
		

		// create a combo box with items specified in the String array:
		final JComboBox<String> bookList = new JComboBox<String>(propList);

		// customize some appearance:
		bookList.setForeground(Color.BLUE);
		bookList.setFont(new Font("Arial", Font.BOLD, 14));
		bookList.setMaximumRowCount(10);
		
		// make the combo box editable so we can add new item when needed
		bookList.setEditable(true);

		// add an event listener for the combo box
		bookList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				selectedProp = (String) combo.getSelectedItem();
			}
		});

		// add event listener for the button Select 
		buttonSell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectedMortSell = "Sell";
			}
		});

		// add event listener for the button Remove
		buttonMortgage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				selectedMortSell = "Mortgage";
			}
		});
		JDialog jd = new JDialog();
		// add components to this frame
		jd.add(bookList);
		jd.add(buttonSell);
		jd.add(buttonMortgage);
		
		jd.pack();
	}
	
	public SellWindow()
	{
		
	}
	public static void main(String args[])
	{
		SellWindow obj = new SellWindow();
		String[] s = new String[2];
		s[0] = "hi";
		s[1] = "There";
		obj.createWindow(s);
	}
	
	public void createWindow(String[] propList) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SellWindow(propList);
			}
		});
	}
}
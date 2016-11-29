package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class ScreenSummary extends JDialog 
{
	private static final long serialVersionUID = 5L;
	private JFrame procFrame;
	
	public ScreenSummary(){ initialize(); }

	private void initialize()
	{
		procFrame = new JFrame();
		procFrame.setBounds(100, 100, 450, 300);
		procFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25,400,25};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
		procFrame.getContentPane().setLayout(gridBagLayout);
		
		// Summary text area
		JScrollPane textScrollPane = new JScrollPane();
		textScrollPane.setSize(400,150);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.anchor = GridBagConstraints.CENTER;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 0;
		procFrame.getContentPane().add(textScrollPane, gbc_pane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setSize(400, 150);
		textScrollPane.add(textPane);
		
		// Buttons label
		JLabel buttonsLabel = new JLabel("Would you like to continue?");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		procFrame.getContentPane().add(buttonsLabel, gbc_label);
		
		// Split pane for two buttons
		JSplitPane splitPane = new JSplitPane(); 
		GridBagConstraints gbc_split = new GridBagConstraints();
		gbc_split.anchor = GridBagConstraints.CENTER;
		gbc_split.gridx = 1;
		gbc_split.gridy = 3;
		procFrame.getContentPane().add(splitPane, gbc_split);
		
		// Continue and exit buttons
		JButton button1 = new JButton("Process next semester");
		splitPane.setLeftComponent(button1);
		JButton button2 = new JButton("Save and quit program");
		splitPane.setRightComponent(button2);
	}
	
}

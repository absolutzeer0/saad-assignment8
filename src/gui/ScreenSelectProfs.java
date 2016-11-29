package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ScreenSelectProfs extends JDialog
{

	private static final long serialVersionUID = 3L;
	private JFrame selFrame;
	
	// Temporary values
	String[] PROFS = {"Dr. One", "Dr. Two", "Dr. Three", "Dr. Four", "Dr. Five", "Dr. Six", "Dr. Seven", "Dr. Eight"};
	String APRIORI = "";
	
	// Constructor
	public ScreenSelectProfs() { initialize(); }

	// Generate the JFrame layout
	private void initialize()
	{
		selFrame = new JFrame();
		selFrame.setBounds(100, 100, 450, 300);
		selFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Split pane between Apriori readout and professor selector
		JSplitPane splitPane = new JSplitPane(); 
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		// Text pane for Apriori readout
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		JTextPane aprioriPane = new JTextPane();
		aprioriPane.setEditable(false);
		aprioriPane.setText(APRIORI);
		scrollPane.add(aprioriPane);

		// JList and button for selecting professors
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		GridBagLayout leftLayout = new GridBagLayout();
		leftLayout.columnWidths = new int[]{115};
		leftLayout.rowHeights = new int[]{0, 28,0,0, 0};
		leftPanel.setLayout(leftLayout);
		
		JList<String> profList = new JList<String>(PROFS);
		profList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_profList = new GridBagConstraints();
		gbc_profList.insets = new Insets(0, 0, 5, 0);
		gbc_profList.gridx = 0;
		gbc_profList.gridy = 0;
		leftPanel.add(profList, gbc_profList);
		
		JButton submitButton = new JButton("Submit");
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.insets = new Insets(0, 0, 5, 0);
		gbc_submitButton.gridx = 0;
		gbc_submitButton.gridy = 1;
		leftPanel.add(submitButton, gbc_submitButton);
		
	}
}

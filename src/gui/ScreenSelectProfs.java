package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScreenSelectProfs extends JSplitPane
{
	private static final long serialVersionUID = 3L;
	
	// Temporary values
	String[] PROFS = {"Dr. One", "Dr. Two", "Dr. Three", "Dr. Four", "Dr. Five", "Dr. Six", "Dr. Seven", "Dr. Eight"};
	String APRIORI = "";
	
	// Constructor
	public ScreenSelectProfs(JFrame parent) 
	{ 
		initialize(parent);
		parent.revalidate();
	}

	// Generate the JFrame layout
	private void initialize(JFrame parent)
	{
		this.setSize(450,300);
		
		// Text pane for Apriori readout
		JScrollPane scrollPane = new JScrollPane();
		this.setRightComponent(scrollPane);
		JTextPane aprioriPane = new JTextPane();
		aprioriPane.setEditable(false);
		aprioriPane.setText(APRIORI);
		scrollPane.add(aprioriPane);

		// JList and button for selecting professors
		JPanel leftPanel = new JPanel();
		this.setLeftComponent(leftPanel);
		GridBagLayout leftLayout = new GridBagLayout();
		leftLayout.columnWidths = new int[]{115};
		leftLayout.rowHeights = new int[]{0, 28,0,0, 0};
		leftPanel.setLayout(leftLayout);
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList profList = new JList(PROFS);
		profList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_profList = new GridBagConstraints();
		gbc_profList.insets = new Insets(0, 0, 5, 0);
		gbc_profList.gridx = 0;
		gbc_profList.gridy = 0;
		leftPanel.add(profList, gbc_profList);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Add call to function that checks if professors are OK
				parent.setContentPane(new ScreenProcRequests(parent));
			}
		});
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.insets = new Insets(0, 0, 5, 0);
		gbc_submitButton.gridx = 0;
		gbc_submitButton.gridy = 1;
		leftPanel.add(submitButton, gbc_submitButton);
		
		// Dispaly this pane in the window
		parent.setContentPane(this);
	}
}

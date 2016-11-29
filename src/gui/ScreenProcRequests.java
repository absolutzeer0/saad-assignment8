package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ScreenProcRequests extends JDialog 
{

	private static final long serialVersionUID = 4L;
	private JFrame procFrame;

	public ScreenProcRequests() { initialize(); }
	
	private void initialize()
	{
		procFrame = new JFrame();
		procFrame.setBounds(100, 100, 450, 300);
		procFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25,400,25};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
		procFrame.getContentPane().setLayout(gridBagLayout);
		
		// Header label
		JLabel headLabel = new JLabel("Processing student requests...");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		procFrame.getContentPane().add(headLabel, gbc_label);
		
		// Main text area
		JScrollPane textScrollPane = new JScrollPane();
		textScrollPane.setSize(400, 150);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.anchor = GridBagConstraints.CENTER;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 1;
		procFrame.getContentPane().add(textScrollPane, gbc_pane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setSize(400, 150);
		textScrollPane.add(textPane);
		
		// 'Continue' button
		JButton button = new JButton("Continue");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.CENTER;
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		procFrame.getContentPane().add(button, gbc_button);
	}
}

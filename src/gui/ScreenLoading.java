package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;

public class ScreenLoading extends JDialog 
{

	private static final long serialVersionUID = 1L;
	private JFrame loadingFrame;
		
	// Constructor
	public ScreenLoading() { initialize(); }
	
	// Generate the JFrame layout
	private void initialize() 
	{
		loadingFrame = new JFrame();
		loadingFrame.setBounds(100, 100, 450, 300);
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 242, 103};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 28, 36, 0, 0, 0};
		loadingFrame.getContentPane().setLayout(gridBagLayout);
		
		// Loading bar label
		JLabel loadLabel = new JLabel("Loading student data...");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1; 
		gbc_label.gridy = 3;
		loadingFrame.getContentPane().add(loadLabel, gbc_label);
		
		// Loading bar
		JProgressBar bar = new JProgressBar();
		bar.setBackground(Color.WHITE);
		bar.setSize(236, 34);
		loadLabel.setLabelFor(bar);
		GridBagConstraints gbc_progBar = new GridBagConstraints();
		gbc_progBar.anchor = GridBagConstraints.CENTER;
		gbc_progBar.gridx = 1; 
		gbc_progBar.gridy = 4;
		loadingFrame.getContentPane().add(bar, gbc_progBar);
	}
	
}

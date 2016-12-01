package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Container;

public class ScreenLoading extends Container
{
	private static final long serialVersionUID = 2L;
		
	// Constructor
	public ScreenLoading(JFrame parent)
	{
		initialize(parent);
		parent.revalidate();
		displayNext(parent);
	}
	
	// Generate the JFrame layout
	private void initialize(JFrame parent) 
	{
		this.setSize(450,300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 242, 103};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 28, 36, 0, 0, 0};
		this.setLayout(gridBagLayout);
		
		// Loading bar label
		JLabel loadLabel = new JLabel("Loading student data...");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1; 
		gbc_label.gridy = 3;
		this.add(loadLabel, gbc_label);
		
		// Loading bar
		JProgressBar bar = new JProgressBar();
		bar.setBackground(Color.WHITE);
		bar.setSize(236, 34);
		loadLabel.setLabelFor(bar);
		GridBagConstraints gbc_progBar = new GridBagConstraints();
		gbc_progBar.anchor = GridBagConstraints.CENTER;
		gbc_progBar.gridx = 1; 
		gbc_progBar.gridy = 4;
		this.add(bar, gbc_progBar);
		
		// Dispaly this pane in the window
		parent.setContentPane(this);
	}	

	// Waits a few seconds and continues; FOR TESTING ONLY!!!
	private void displayNext(JFrame parent)
	{
		try
		{
			// TODO: Remove this sleep once actual loading function exists
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception err)
		{
			err.printStackTrace(System.err);
		}

		parent.setContentPane(new ScreenSelectProfs(parent));
	}
	
}

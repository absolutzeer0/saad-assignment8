package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class ScreenNewCont extends JDialog 
{

	private static final long serialVersionUID = 2L;
	private JFrame mainFrame;
	
	// Screen constructor
	public ScreenNewCont() { initialize(); }

	// Generate the JFrame layout
	private void initialize() 
	{
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 242, 103};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
		mainFrame.getContentPane().setLayout(gridBagLayout);
		
		// Welcome label
		JLabel label = new JLabel();
		label.setText("Welcome, user!");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1; 
		gbc_label.gridy = 3;
		mainFrame.getContentPane().add(label, gbc_label);
		
		// Split pane to hold two buttons
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.CENTER;
		gbc_splitPane.gridx = 1; 
		gbc_splitPane.gridy = 5;
		mainFrame.getContentPane().add(splitPane, gbc_splitPane);
		
		// Continue and New buttons
		JButton btnContinue = new JButton("Continue");
		splitPane.setLeftComponent(btnContinue);
		// TODO: Link this button to screen transition and data loading functions
		
		JButton btnNewInstance = new JButton("New Instance");
		splitPane.setRightComponent(btnNewInstance);
		// TODO: Link this button to clearing old data and loading new data functions
	}
	
}

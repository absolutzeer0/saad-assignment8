package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class ScreenNewCont extends Container
{
	private static final long serialVersionUID = 1L;
	
	// Screen constructor
	public ScreenNewCont(MainWindow parent)
	{
		initialize(parent);
		parent.revalidate();
	}

	// Generate the JFrame layout
	private void initialize(MainWindow parent) 
	{
		this.setSize(450,300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 242, 103};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
		this.setLayout(gridBagLayout);
		
		// Welcome label
		JLabel label = new JLabel();
		label.setText("Welcome, user!");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1; 
		gbc_label.gridy = 3;
		this.add(label, gbc_label);
		
		// Split pane to hold two buttons
		JSplitPane splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.CENTER;
		gbc_splitPane.gridx = 1; 
		gbc_splitPane.gridy = 5;
		this.add(splitPane, gbc_splitPane);
		
		// Continue and New buttons
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener()
		{
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Ensure a previous run occurred that we can continue from
                        boolean canContinue = parent.testForPreviousRun();
                        parent.setContentPane(new ScreenSelectProfs(parent, canContinue));
                    }
		});
		splitPane.setLeftComponent(btnContinue);
		
		JButton btnNewInstance = new JButton("New Instance");
		btnNewInstance.addActionListener(new ActionListener()
		{
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        parent.getGuiInterface().incrementCycle();                     
                        parent.getGuiInterface().incrementCycle();                     
                        parent.setContentPane(new ScreenSelectProfs(parent, false));
                    }
		});
		splitPane.setRightComponent(btnNewInstance);
		
		// Dispaly this pane in the window
		parent.setContentPane(this);
	}
	
}

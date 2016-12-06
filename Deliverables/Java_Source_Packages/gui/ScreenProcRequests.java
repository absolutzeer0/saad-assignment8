package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ScreenProcRequests extends Container
{
	private static final long serialVersionUID = 4L;
        private String baseDir;
	public ScreenProcRequests(MainWindow parent,String baseD)
	{
            baseDir = baseD;
		initialize(parent);
		parent.revalidate();
	}
	private void initialize(MainWindow parent)
	{
		this.setSize(450,300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25,400,25};
		gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
		this.setLayout(gridBagLayout);
		
		// Header label
		JLabel headLabel = new JLabel("Processing student requests...");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.CENTER;
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		this.add(headLabel, gbc_label);
		
		// Main text area
                JPanel auxP = new JPanel();
                
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setSize(400, 150);
		GridBagConstraints gbc_pane = new GridBagConstraints();
		gbc_pane.anchor = GridBagConstraints.CENTER;
		gbc_pane.gridx = 1;
		gbc_pane.gridy = 1;
                
                JScrollPane jb = new JScrollPane(textPane);
                auxP.setSize(600, 400);
                auxP.add(jb);
		this.add(auxP, gbc_pane);
                
                // Handle the student's requests
                int cycle = parent.getGuiInterface().getCurrentCycle();
                ArrayList<String> reqs = parent.getGuiInterface().handleRequests(cycle, baseDir);
                String message = "";
                for(String s : reqs)
                {
                    message += s+"\n";
                }
                textPane.setText(message);
		
		// 'Continue' button
		JButton button = new JButton("Continue");
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setContentPane(new ScreenSummary(parent));
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.CENTER;
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		this.add(button, gbc_button);
		
		// Dispaly this pane in the window
		parent.setContentPane(this);
	}
}

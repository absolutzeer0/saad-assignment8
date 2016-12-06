package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class ScreenSummary extends Container
{
	private static final long serialVersionUID = 5L;
	
	public ScreenSummary(MainWindow parent)
	{
            initialize(parent);
            parent.revalidate();
	}

	private void initialize(MainWindow parent)
	{
            GridBagLayout gridBagLayout = new GridBagLayout();
            gridBagLayout.columnWidths = new int[]{25,400,25};
            gridBagLayout.rowHeights = new int[]{28, 0, 0, 0, 0, 0, 0, 0};
            this.setLayout(gridBagLayout);

            // Summary text area
            JPanel jp = new JPanel();
            jp.setSize(800,600);
            JTextPane textPane = new JTextPane();
            textPane.setEditable(false);
            textPane.setSize(600, 400);
            textPane.setText(parent.getGuiInterface().generateSummary());
            GridBagConstraints gbc_pane = new GridBagConstraints();
            gbc_pane.anchor = GridBagConstraints.CENTER;
            gbc_pane.gridx = 1;
            gbc_pane.gridy = 0;
            JScrollPane jScroll = new JScrollPane(textPane);
            jp.add(jScroll);
            this.add(jp, gbc_pane);

            // Buttons label
            JLabel buttonsLabel = new JLabel("Would you like to continue?");
            GridBagConstraints gbc_label = new GridBagConstraints();
            gbc_label.anchor = GridBagConstraints.CENTER;
            gbc_label.gridx = 1;
            gbc_label.gridy = 2;
            this.add(buttonsLabel, gbc_label);

            // Split pane for two buttons
            JSplitPane splitPane = new JSplitPane(); 
            GridBagConstraints gbc_split = new GridBagConstraints();
            gbc_split.anchor = GridBagConstraints.CENTER;
            gbc_split.gridx = 1;
            gbc_split.gridy = 3;
            this.add(splitPane, gbc_split);

            // Continue and exit buttons
            JButton button1 = new JButton("Process next semester");
            button1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        parent.getGuiInterface().saveDataToFiles();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                        Logger.getLogger(ScreenSummary.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finally
                    {
                        parent.getGuiInterface().incrementCycle();
                        parent.setContentPane(new ScreenSelectProfs(parent, true));
                    }
                }
            });
            splitPane.setLeftComponent(button1);

            JButton button2 = new JButton("Save and quit program");
            button2.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        parent.getGuiInterface().saveDataToFiles();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                        Logger.getLogger(ScreenSummary.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showConfirmDialog(
                            null,
                            "Error saving files.  Next execution may not start at correct cycle.",
                            "Saving error",
                            JOptionPane.OK_OPTION,
                            JOptionPane.ERROR_MESSAGE
                            );
                    }
                    finally
                    {
                        // Close out the program
                        parent.dispose();
                    }
                }
            });
            splitPane.setRightComponent(button2);

            // Dispaly this pane in the window
            parent.setContentPane(this);
	}
	
}

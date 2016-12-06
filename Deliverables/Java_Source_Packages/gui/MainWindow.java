package gui;

import backend.InterfaceGuiLogic;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame
{
    private static final long serialVersionUID = 0L;
    private static InterfaceGuiLogic igl;

    public MainWindow()
    {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(700,800);
        this.setTitle("Group 10 Student Management System");
        this.setContentPane(new ScreenNewCont(this));
        this.setVisible(true);
        this.addWindowListener(new CloseListener());
        
        igl = new InterfaceGuiLogic();
    }

    public InterfaceGuiLogic getGuiInterface() { return igl; }
    
    public boolean testForPreviousRun()
    {
        int cycleNum = igl.getCurrentCycle();
        if(cycleNum == -1)
        {
            JOptionPane.showConfirmDialog(
                null,
                "Old files do not exist or are corrupted.  Starting a new cycle.",
                "Loading error",
                JOptionPane.OK_OPTION,
                JOptionPane.ERROR_MESSAGE
                );
            return false;
        }
        return true;
    }
    
    private class CloseListener implements WindowListener
    {
        public CloseListener(){}
        
        @Override
        public void windowClosing(WindowEvent e)
        {
            int result = JOptionPane.showConfirmDialog(
                null,
                "Are you sure?  Closing now will not save data from current cycle.",
                "Exit Program",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
                );

            if(result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
        
        // Override the rest of the WindowListener methods.  They don't do anything.
        @Override
        public void windowOpened(WindowEvent e) {}
        @Override
        public void windowClosed(WindowEvent e) {}
        @Override
        public void windowIconified(WindowEvent e) {}
        @Override
        public void windowDeiconified(WindowEvent e) {}
        @Override
        public void windowActivated(WindowEvent e) {}
        @Override
        public void windowDeactivated(WindowEvent e) {}
    }



}

package gui;

import javax.swing.JFrame;

public class MainWindow extends JFrame 
{
	private static final long serialVersionUID = 0L;

	public MainWindow()
	{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 300);
        this.setTitle("Group 10 Student Management System");
        this.setContentPane(new ScreenNewCont(this));
        this.setVisible(true);
	}
	
	// Testing function
	public static void main(String[] args)
	{
		new MainWindow();
	}
	
}

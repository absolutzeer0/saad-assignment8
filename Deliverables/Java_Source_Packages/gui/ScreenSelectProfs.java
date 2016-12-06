package gui;

import backend.Employee;
import backend.InterfaceGuiLogic;
import backend.Professor;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;

import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ScreenSelectProfs extends JSplitPane
{
    private static final long serialVersionUID = 3L;
    MainWindow win;
    ArrayList<Employee> profList;
    ArrayList<String> profsSelected;
    ArrayList<String> coursesSelected;
    

    // Constructor
    public ScreenSelectProfs(MainWindow parent, boolean isContinue) 
    {
        initialize(parent, isContinue);
        win = parent;
        profsSelected = new ArrayList<>();
        coursesSelected = new ArrayList<>();
        parent.revalidate();
    }

    // Generate the JFrame layout
    private void initialize(MainWindow parent, boolean isContinue)
    {
        InterfaceGuiLogic guiInt = parent.getGuiInterface();

        // Load the data
        // If continuing, use files in save directory
        String baseDir = isContinue ? parent.getGuiInterface().saveDir : "";
        System.out.println("Base Dir SCreen new COnt:"+baseDir);
        guiInt.loadFiles(baseDir);
        String apriori = guiInt.digestFileWithWeka(new File(baseDir+"records.csv"));

        // Load list of professors names
        int numProfs = guiInt.getU().getEmployees().size();
        String[] profNames = new String[numProfs];
        profList = guiInt.getU().getEmployees();
        for(int i = 0; i < numProfs; i++)
        {
            profNames[i] = profList.get(i).getName();
        }

        // Text pane for Apriori readout
        JTextPane aprioriPane = new JTextPane();
        aprioriPane.setEditable(false);
        aprioriPane.setText(apriori);
        this.setRightComponent(aprioriPane);

        // JList and button for selecting professors
        JPanel leftPanel = new JPanel();
        this.setLeftComponent(leftPanel);
        GridBagLayout leftLayout = new GridBagLayout();
        leftLayout.columnWidths = new int[]{115};
        leftLayout.rowHeights = new int[]{0, 28,0,0, 0};
        leftPanel.setLayout(leftLayout);

        @SuppressWarnings({ "rawtypes", "unchecked" })
        JList profJList = new JList(profNames);
        profJList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        profJList.addListSelectionListener(new ProfListListener());
        GridBagConstraints gbc_profList = new GridBagConstraints();
        gbc_profList.insets = new Insets(0, 0, 5, 0);
        gbc_profList.gridx = 0;
        gbc_profList.gridy = 0;
        leftPanel.add(profJList, gbc_profList);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getGuiInterface().setSelectedAssigments(coursesSelected);
                parent.setContentPane(new ScreenProcRequests(parent,baseDir));
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
    
    private class ProfListListener implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
            // Ensure we haven't hit our max
            if(profsSelected.size() == win.getGuiInterface().getU().getMaxProfCount())
            {
                JOptionPane.showConfirmDialog(null,
                    "Max number of professors already reached.",
                    "Course Selection",
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            // Get selected professor's list of teachable courses
            Professor prof = (Professor) profList.get(e.getLastIndex());
            ArrayList<String> qualifiedCourses = win.getGuiInterface().getU().getCoursesNames();
            
            // Compare professor to list of professors selected
            if(profsSelected.contains(prof.getName()))
            {
                JOptionPane.showConfirmDialog(null,
                    "Professor "+prof.getName()+" has already been selected.",
                    "Course Selection",
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            if(qualifiedCourses == null)
            {
                JOptionPane.showConfirmDialog(null,
                    "Professor "+prof.getName()+" is not qualified to teach any courses.",
                    "Course Selection",
                    JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE
                );
            }
            else
            {
                // Create dialog to selcet class for that professor
                Object[] objs = qualifiedCourses.toArray();
                String selection = (String) JOptionPane.showInputDialog(null,
                    "Which course should this "+prof.getName()+" teach?",
                    "Course Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    objs,
                    objs[1]
                );
                
                // Compare selection to courses already picked
                if(coursesSelected.contains(selection))
                {
                    JOptionPane.showConfirmDialog(null,
                        "Course "+selection+" already has a teacher.",
                        "Course Selection",
                        JOptionPane.OK_OPTION,
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                // If we get here, selection is valid
                profsSelected.add(prof.getName());
                coursesSelected.add(prof.getName()+","+selection);
            }
        }
    }
    
}

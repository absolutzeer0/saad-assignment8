package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge
 */
public class InterfaceGuiLogic {

    private University u;
    private String nameOfFirstDocument = "students.csv";
    private String nameOfSecondDocument = "courses.csv";
    private String nameOfThirdDocument = "instructors.csv";
    private String nameOfFourthDocument = "records.csv";
    private String nameOfFithtDocument = "prereqs.csv";
    private String nameOfSixthDocument = "waitlist.txt";
    private int currentCycle = -1;

    private ArrayList<String> loadedAssigments;
    private ArrayList<String> selectedAssigments;
    private ArrayList<String> unselectedAssigments;
    private ArrayList<String> requests;
    private LinkedList<String> waitListedRequests;
    private int requestsGranted;
    private int requestsDenied;
    private int totalRequestsGranted = 0;
    private int totalRequestsDenied = 0;

    public final String saveDir = "/home/ubuntu/.StuManSys/";
    //public final String saveDir = "C:\\z\\";

    public InterfaceGuiLogic() {
        waitListedRequests = new LinkedList<>();
    }

    public String digestFileWithWeka(File inputFile) {

        System.out.println("Path Digest:" + inputFile.getAbsolutePath());
        WekaConverter wc = new WekaConverter();
        try {
            return wc.aprioriReadout(inputFile);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void loadFiles(String filesDir) {

        BufferedReader readerF = null;

        try {
            readerF = new BufferedReader(new FileReader(nameOfFirstDocument));

            String aux;
            ArrayList<Student> students = new ArrayList<>();
            while ((aux = readerF.readLine()) != null) {
                //System.out.println(aux);
                StringTokenizer tk = new StringTokenizer(aux, ",");

                while (tk.hasMoreElements()) {
                    String idS = tk.nextToken();
                    String nameS = tk.nextToken();
                    String addressS = tk.nextToken();
                    String phoneS = tk.nextToken();
                    Student sAux = new Student(idS, nameS, addressS, phoneS);
                    students.add(sAux);
                }
            }
            // System.out.println(students.toString());
            readerF.close();
            readerF = new BufferedReader(new FileReader(nameOfSecondDocument));
            String auxCourses;
            ArrayList<Course> courses = new ArrayList<>();
            while ((auxCourses = readerF.readLine()) != null) {
                // System.out.println(auxCourses);
                String[] splittedCourseS = auxCourses.split(",");

                //  System.out.println(tk.nextToken());
                String idC = splittedCourseS[0];
                String nameC = splittedCourseS[1];
                Course cAux = null;
                if (splittedCourseS.length > 2) {
                    ArrayList<String> semestersOfe = new ArrayList<>();
                    for (int i = 2; i < splittedCourseS.length; i++) {
                        semestersOfe.add(splittedCourseS[i]);
                    }
                    cAux = new Course(idC, nameC, semestersOfe);
                } else {
                    cAux = new Course(idC, nameC, null);
                }

                courses.add(cAux);

            }
            readerF.close();
            readerF = new BufferedReader(new FileReader(nameOfThirdDocument));
            //System.out.println(courses.toString());
            String auxP;
            ArrayList<Employee> professors = new ArrayList<>();
            while ((auxP = readerF.readLine()) != null) {
                //   System.out.println(auxP);
                StringTokenizer tk = new StringTokenizer(auxP, ",");

                while (tk.hasMoreElements()) {

                    String idP = tk.nextToken();
                    String nameP = tk.nextToken();
                    String addressP = tk.nextToken();
                    String phoneP = tk.nextToken();

                    Professor pAux = new Professor(idP, nameP, addressP, phoneP);
                    professors.add(pAux);
                }
            }
            readerF.close();
            readerF = new BufferedReader(new FileReader(filesDir + nameOfFourthDocument));
            // System.out.println(professors.toString());
            String auxR;
            ArrayList<Record> records = new ArrayList<>();
            while ((auxR = readerF.readLine()) != null) {
                //System.out.println(auxR);
                String[] tk = auxR.split(",");
                String idS = tk[0];
                String idC = tk[1];
                String idP = tk[2];
                String comments = tk[3];
                String finalGrade = tk[4];
                Record rAux = new Record(idS, idC, idP, comments, finalGrade);
                records.add(rAux);
            }
            u = new University(students, professors, courses, records);
            readerF = new BufferedReader(new FileReader(nameOfFithtDocument));
            auxR = "";

            while ((auxR = readerF.readLine()) != null) {
                String[] tk = auxR.split(",");
                String prerequesite = tk[0];
                String idCourseToAddPrerequsite = tk[1];
                u.addPrerequiste(prerequesite, idCourseToAddPrerequsite);
            }

            // Read in waitlist, if it exists
            if (this.currentCycle > 1) {
                waitListedRequests = new LinkedList<>();
                readerF = new BufferedReader(new FileReader(saveDir + nameOfSixthDocument));
                auxR = "";
                while ((auxR = readerF.readLine()) != null) {
                    waitListedRequests.add(auxR);
                }
            }

            readerF.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadAssigmentsFileToDisplay(int cycle, String filesDir) {
        loadedAssigments = new ArrayList<>();
        selectedAssigments = new ArrayList<>();

        try {
            String nameOfSixthDocument = filesDir + "assignments_";
            nameOfSixthDocument += cycle + ".csv";
            BufferedReader readerF = new BufferedReader(new FileReader(filesDir + nameOfSixthDocument));
            String auxR = "";
            int counter = 0;
            while ((auxR = readerF.readLine()) != null) {
                loadedAssigments.add(counter + ": " + auxR);
                counter++;
            }
            readerF.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        unselectedAssigments = new ArrayList<>(loadedAssigments);
    }

    public String addAssignment(int index) {
        //String[] tk = loadedAssigments.get(index).substring(1,loadedAssigments.get(index).length()-1).split(",");        
        StringTokenizer tk = new StringTokenizer(loadedAssigments.get(index).substring(3, loadedAssigments.get(index).length()), ",");
        String idProfessor = tk.nextToken();
        String idCourse = tk.nextToken();
        String capStudents = tk.nextToken();
        String returnAssigment = doAssigment(idProfessor, idCourse, Integer.parseInt(capStudents));
        if (returnAssigment.equalsIgnoreCase("succeded")) {
            selectedAssigments.add(loadedAssigments.get(index));
            unselectedAssigments.remove(index);
            return "instructor selected!";
        } else {
            return returnAssigment;
        }

    }

    public String doAssigment(String idProfessor, String idCourse, int capStudents) {
        return u.doAssigments(idProfessor, idCourse, capStudents);
    }

    public ArrayList<String> handleRequests(int cycle, String filesDir) {
        requests = new ArrayList<>();
        BufferedReader readerF = null;
        try {
            String nameOfSeventhDocument = "" + "requests_";
            nameOfSeventhDocument += cycle + ".csv";
            System.out.println(nameOfSeventhDocument + "size: " + waitListedRequests.size());
            readerF = new BufferedReader(new FileReader(nameOfSeventhDocument));
            String auxR = "";

            requestsGranted = 0;
            requestsDenied = 0;
            Iterator<String> it = waitListedRequests.iterator();

            while (it.hasNext()) {
                String auxIt = (String) (it.next());
                System.out.println("auxIt");
                StringTokenizer tk = new StringTokenizer(auxIt, ",");
                String studentID = tk.nextToken();
                String courseIDRequested = tk.nextToken();
                String aux = u.handleRequestStudent(studentID, courseIDRequested);
                if (aux.equalsIgnoreCase("valid")) {
                    waitListedRequests.remove(auxIt);
                    requestsGranted++;
                } else if (aux.equalsIgnoreCase("no remaining seats available for the course at this time")) {
                    waitListedRequests.add(studentID + "," + courseIDRequested);
                } else {
                    requestsDenied++;
                }
                requests.add("request(" + studentID + ", " + courseIDRequested + "): " + aux);
            }

            while ((auxR = readerF.readLine()) != null) {
                System.out.println(auxR);
                String[] tk = auxR.split(",");
                String studentID = tk[0];
                String courseIDRequested = tk[1];
                String aux = u.handleRequestStudent(studentID, courseIDRequested);
                if (aux.equalsIgnoreCase("no remaining seats available for the course at this time")) {
                    if (!hasRequest(studentID + "," + courseIDRequested)) {
                        waitListedRequests.add(studentID + "," + courseIDRequested);
                    }
                }
                requests.add("request(" + studentID + ", " + courseIDRequested + "): " + aux);
            }

            this.totalRequestsGranted += requestsGranted;
            this.totalRequestsDenied += requestsDenied;
            readerF.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                readerF.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            return requests;
        }
    }

    public String generateSummary() {
        // Get semester statistics
        // TODO: Finish this section
        String summary = "Semester statistics\n";
        summary += "Examined: " + requests.size() + " Granted: " + requestsGranted + " Failed: " + requestsDenied + " WaitListed: " + waitListedRequests.size();

        // Get total data
        // TODO: Finish this section
        summary += "\nTotal Statistics\n";
        summary += "Examined: " + requests.size() + " Granted: " + totalRequestsGranted + " Failed: " + totalRequestsDenied + " WaitListed: " + waitListedRequests.size();
        // Get all records
        summary += "\nAcademic Records\n";
        for (Record r : getU().getRecords()) {
            summary += r.getIdStudent() + ", ";
            summary += r.getIdCourse() + ", ";
            summary += r.getIdProfessor() + ", ";
            summary += r.getCommentsProfessor() + ", ";
            summary += r.getPerformace() + "\n";
        }

        return summary;
    }

    public void saveDataToFiles() throws IOException {
        // The current records, waitlist, and cycle should be saved
        if (currentCycle == 1) {
            File f = new File(saveDir + "records.csv");
            f.createNewFile();
            FileWriter writer = new FileWriter(f);

            for (Record r : u.getRecords()) {
                writer.write(r.getIdStudent() + ","
                        + r.getIdCourse() + ","
                        + r.getIdProfessor() + ","
                        + r.getCommentsProfessor() + ","
                        + r.getPerformace() + "\n");
                System.out.println("Suposedly printing:" + r.getIdStudent() + ","
                        + r.getIdCourse() + ","
                        + r.getIdProfessor() + ","
                        + r.getCommentsProfessor() + ","
                        + r.getPerformace() + "\n");
            }
            writer.close();
            File f2 = new File(saveDir + "waitlist.txt");
            f2.createNewFile();
            writer = new FileWriter(f2);
            for (String req : waitListedRequests) {
                writer.write(req + "\n");
                System.out.println("Suposed WaitL:" + req + "\n");
            }
            writer.close();
            File f3 = new File(saveDir + "cycle.txt");
            f3.createNewFile();
            writer = new FileWriter(f3);
            writer.write("" + getCurrentCycle());

            writer.close();
        } else {
            File f = new File(saveDir + "records.csv");
            
            FileWriter writer = new FileWriter(f);
            for (Record r : u.getRecords()) {
                writer.write(r.getIdStudent() + ","
                        + r.getIdCourse() + ","
                        + r.getIdProfessor() + ","
                        + r.getCommentsProfessor() + ","
                        + r.getPerformace() + "\n");
                System.out.println("Suposedly printing:" + r.getIdStudent() + ","
                        + r.getIdCourse() + ","
                        + r.getIdProfessor() + ","
                        + r.getCommentsProfessor() + ","
                        + r.getPerformace() + "\n");
            }
            writer.close();
            File f2 = new File(saveDir + "waitlist.txt");            
            writer = new FileWriter(f2);
            for (String req : waitListedRequests) {
                writer.write(req + "\n");
                System.out.println("Suposed WaitL:" + req + "\n");
            }
            writer.close();
            File f3 = new File(saveDir + "cycle.txt");            
            writer = new FileWriter(f3);
            writer.write("" + getCurrentCycle());

            writer.close();
        }
    }

    public University getU() {
        return u;
    }

    public void setU(University u) {
        this.u = u;
    }

    public ArrayList<String> getLoadedAssigments() {
        return loadedAssigments;
    }

    public void setLoadedAssigments(ArrayList<String> loadedAssigments) {
        this.loadedAssigments = loadedAssigments;
    }

    public ArrayList<String> getSelectedAssigments() {
        return selectedAssigments;
    }

    public void setSelectedAssigments(ArrayList<String> selectedAssigments) {
        this.selectedAssigments = selectedAssigments;
    }

    public ArrayList<String> getUnselectedAssigments() {
        return unselectedAssigments;
    }

    public void setUnselectedAssigments(ArrayList<String> unselectedAssigments) {
        this.unselectedAssigments = unselectedAssigments;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public LinkedList<String> getWaitListedRequests() {
        return waitListedRequests;
    }

    public void setWaitListedRequests(LinkedList<String> waitListedRequests) {
        this.waitListedRequests = waitListedRequests;
    }

    public int getCurrentCycle() {
        int cycle = currentCycle;
        FileReader cycleFile = null;

        if (cycle == -1) {
            try {
                cycleFile = new FileReader(saveDir + "cycle.txt");
                BufferedReader bufRead = new BufferedReader(cycleFile);
                String cycleString;
                if ((cycleString = bufRead.readLine()) != null) {
                    cycle = Integer.getInteger(cycleString);
                }
                cycleFile.close();
            } catch (Exception ex) {

                ex.printStackTrace();
                Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
                return -1;
            } finally {
                if (cycleFile != null) {
                    try {
                        cycleFile.close();
                    } catch (IOException ex) {
                        Logger.getLogger(InterfaceGuiLogic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        return cycle;
    }

    public void incrementCycle() {
        currentCycle += 1;
    }

    // Goes through the waitlist to see if request already exists
    private boolean hasRequest(String string) {
        for (String s : waitListedRequests) {
            if (s.matches(string)) {
                return true;
            }
        }
        // If we get through the whole list without a match...
        return false;
    }

}

package backend;

import gui.MainWindow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge
 */
public class Main {

    public static void main(String args[]) {

        InterfaceGuiLogic iG = new InterfaceGuiLogic();
        MainWindow win = new MainWindow();
        
    /*
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        while (run) {
            System.out.println("Continue Simulation: [yes/no]");
            String haltSystem = "";
            haltSystem = in.readLine();

            if (haltSystem.contains("yes")) {
                iG.loadFiles();
                int cycle = 1;
                iG.loadAssigmentsFileToDisplay(cycle);
                iG.handleRequests(cycle);

                String options = "";

                System.out.println(iG.digestFileWithWeka(new File("records.csv")));
                boolean run2 = true;
                while (run2) {
                    options = in.readLine();
                    if (options.equals("display")) {
                        System.out.println("% --- selected ---");
                        for (int i = 0; i < iG.getSelectedAssigments().size(); i++) {
                            System.out.println(iG.getSelectedAssigments().get(i));
                        }
                        System.out.println("% --- unselected ---");
                        for (int i = 0; i < iG.getUnselectedAssigments().size(); i++) {
                            System.out.println(iG.getUnselectedAssigments().get(i));
                        }
                    } else if ((options.contains("add"))) {
                        String[] tk = options.split(",");
                        String idC = tk[1];
                        System.out.println(iG.addAssignment(Integer.parseInt(idC)));
                    } else if (options.contains("add_record")) {
                        String[] tk = options.split(",");

                        String idS = tk[1];
                        String idC = tk[2];
                        String idP = tk[3];
                        String comments = tk[4];
                        String finalGrade = tk[5];

                        Record rAux = new Record(idS, idC, idP, comments, finalGrade);
                        iG.getU().addRecord(rAux);

                    } else if (options.equals("submit")) {
                        System.out.println("selections finalized - now processing requests for Semester " + cycle);
                        System.out.println("Processed Requests ");

                        for (int j = 0; j < iG.getRequests().size(); j++) {
                            System.out.println(iG.getRequests().get(j));
                        }
                        System.out.println("Academic Records");
                        for (int i = 0; i < iG.getU().getRecords().size(); i++) {
                            System.out.println(iG.getU().getRecords().get(i).getIdStudent() + ", " + iG.getU().getRecords().get(i).getIdCourse()
                                    + ", " + iG.getU().getRecords().get(i).getIdProfessor() + ", " + iG.getU().getRecords().get(i).getCommentsProfessor()
                                    + ", " + iG.getU().getRecords().get(i).getPerformace());
                        }

                        System.out.println("Waitlisted Students");
                        Iterator<String> it = iG.getWaitListedRequests().iterator();
                        int indexW = 0;
                        while (it.hasNext()) {
                            String aux = it.next();
                            System.out.println((indexW + 1) + "#: " + aux);
                        }

                        cycle++;
                        iG.loadAssigmentsFileToDisplay(cycle);
                        run2 = false;
                    }
                }
            } else {
                System.exit(0);
            }

        }
*/
        //iG.doAssigments(cycle);        
        //iG.handleRequests(cycle);
/*
        boolean run = true;
        while (run) {
            options = in.readLine();

            if (options.equals("display_requests")) {
                for (int i = 0; i < iG.getU().getRequests().size(); i++) {
                    if (iG.getU().getRequests().get(i).isAccepted()) {
                        System.out.println(iG.getU().getRequests().get(i).getStudent().getUuid()
                                + ", " + iG.getU().getRequests().get(i).getStudent().getNameStudent() + ", "
                                + iG.getU().getRequests().get(i).getCourseRequested().getIdCourse() + ", "
                                + iG.getU().getRequests().get(i).getCourseRequested().getNameCourse());
                    }
                }
            } else if (options.equals("display_seats")) {
                for (int i = 0; i < iG.getU().getCourses().size(); i++) {
                    System.out.println(iG.getU().getCourses().get(i).getIdCourse() + ", " + iG.getU().getCourses().get(i).getNameCourse() + ", "
                            + (iG.getU().getCourses().get(i).getCapStudents() - iG.getU().getCourses().get(i).getStudentsRegistered()));
                }
            } else if (options.equals("display_records")) {
                for (int i = 0; i < iG.getU().getRecords().size(); i++) {
                    System.out.println(iG.getU().getRecords().get(i).getIdStudent() + ", " + iG.getU().getRecords().get(i).getIdCourse()
                            + ", " + iG.getU().getRecords().get(i).getIdProfessor() + ", " + iG.getU().getRecords().get(i).getCommentsProfessor()
                            + ", " + iG.getU().getRecords().get(i).getPerformace());
                }
            } else if (options.contains("add_record")) {
                String[] tk = options.split(",");

                String idS = tk[1];
                String idC = tk[2];
                String idP = tk[3];
                String comments = tk[4];
                String finalGrade = tk[5];

                Record rAux = new Record(idS, idC, idP, comments, finalGrade);
                iG.getU().addRecord(rAux);

            } else if (options.contains("add_seats")) {
                String optS[] = options.split(",");
                iG.getU().addSeats(optS[1], Integer.parseInt(optS[2]));
            } else if (options.contains("check_request")) {
                String optS[] = options.split(",");
                System.out.println(iG.getU().handleRequestStudent(optS[1], optS[2]));
            } else if (options.equals("quit")) {
                System.out.println("stopping the command loop");
                run = false;
                in.close();
            }
        }
         */
    }
}

package backend;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge
 */
public class University {

    private String nameUniversity;
    private ArrayList<Student> students;
    private ArrayList<Employee> employees;
    private ArrayList<Course> courses;
    private ArrayList<Record> records;
    private ArrayList<Request> requests;
    private final int maximumHiringProfesorsPerSemester = 5;

    public University(ArrayList<Student> students, ArrayList<Employee> employees, ArrayList<Course> courses, ArrayList<Record> records) {
        this.students = students;
        this.employees = employees;
        this.courses = courses;
        this.records = records;
        requests = new ArrayList<>();
    }
    
    public int getMaxProfCount() { return maximumHiringProfesorsPerSemester; }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public boolean createCourse(Course newCourse) {

        return true;
    }

    public boolean createProfessor(Professor newProfessor) {

        return true;
    }

    public boolean createStudent(Student newStundent) {

        return true;
    }

    public boolean createCounselor(Counselor newCounselor) {
        return true;
    }

    public ArrayList<Student> queryAllStudents() {
        return null;
    }

    public ArrayList<Student> queryGraduateStudents() {
        return null;
    }

    public ArrayList<Student> queryCurrentStudents() {
        return null;
    }

    public Student querySpecificStudent(String idStudent) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getUuid().equals(idStudent)) {
                return this.students.get(i);
            }
        }
        return null;
    }

    public Professor querySpecificProfessor(String idProfessor) {
      
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof Professor) {
                if(employees.get(i).getUid().equalsIgnoreCase(idProfessor)){
                    return (Professor)employees.get(i);
                }
            }
        }
        return null;
    }

    public Course querySpecificCourse(String idCourse) {

        for (int i = 0; i < this.courses.size(); i++) {
            if (this.courses.get(i).getIdCourse().equals(idCourse)) {
                return this.courses.get(i);
            }
        }
        return null;
    }

    public ArrayList<Professor> queryCurrentProfessors() {
        return null;
    }

    public ArrayList<Professor> queryAllProfessors() {
        return null;
    }

    public boolean capProfessorsToHireReached() {
        return true;
    }

    public boolean capStudentsReached() {
        return true;
    }

    public boolean capCoursesReached() {
        return true;
    }

    public ArrayList<Professor> queryCurrentProfessorsWithSkills(ArrayList<String> degreesToMatch) {
        return null;
    }

    public ArrayList<Professor> queryProfessorsTaughtInthePast(String idCourse) {
        return null;
    }

    public int studentsWhoHaventTakenClasses() {

        int countStudents = 0;
        for (int i = 0; i < students.size(); i++) {
            boolean studentIsTakenCourse = false;
            for (int j = 0; j < records.size(); j++) {
                if (students.get(i).getUuid().equalsIgnoreCase(records.get(j).getIdStudent())) {
                    studentIsTakenCourse = true;
                    break;
                }
            }
            if (!studentIsTakenCourse) {
                countStudents++;
            }
        }
        return countStudents;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public ArrayList<String> getCoursesNames() {
        ArrayList<String> names = new ArrayList<>();
        for(Course c : courses) { names.add(c.getNameCourse()); }
        return names;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public ArrayList<Record> queryRecordsFromStudent(String idStudent) {
        ArrayList<Record> recordsStudent = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            if (this.records.get(i).getIdStudent().equals(idStudent)) {
                recordsStudent.add(this.records.get(i));
            }
        }
        return recordsStudent;
    }

    public int getNumProfessors() {
        int countP = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof Professor) {
                countP++;
            }
        }
        return countP;
    }

    public int professorsWhoHaventTaughtClasses() {

        int countP = 0;
        for (int i = 0; i < employees.size(); i++) {
            boolean professorIsTakenCourse = false;
            if (employees.get(i) instanceof Professor) {
                for (int j = 0; j < records.size(); j++) {
                    if (employees.get(i).getUid().equalsIgnoreCase(records.get(j).getIdProfessor())) {
                        professorIsTakenCourse = true;
                        break;
                    }
                }
            }
            if (!professorIsTakenCourse) {
                countP++;
            }
        }
        return countP;
    }

    public ArrayList<Professor> professorsWithCurrentClasses() {
        ArrayList<Professor> professorWithClasses = new ArrayList<>();
        //int countP = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i) instanceof Professor) {
                if (((Professor) (employees.get(i))).getCurrentCourse() != null) {
                    //countP++;
                    professorWithClasses.add(((Professor) (employees.get(i))));
                }
            }
        }
        return professorWithClasses;
    }

    public int coursesWithoutStudents() {

        int countC = 0;
        for (int i = 0; i < courses.size(); i++) {
            boolean professorIsTakenCourse = false;
            for (int j = 0; j < records.size(); j++) {
                if (courses.get(i).getIdCourse().equalsIgnoreCase(records.get(j).getIdCourse())) {
                    professorIsTakenCourse = true;
                    break;
                }
            }
            if (!professorIsTakenCourse) {
                countC++;
            }
        }
        return countC;
    }

    public int queryCoursesOfferedInATerm(String term) {
        int countC = 0;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getSemestersOffered() != null) {
                for (int k = 0; k < courses.get(i).getSemestersOffered().size(); k++) {
                    if (courses.get(i).getSemestersOffered().get(k).equalsIgnoreCase(term)) {
                        countC++;
                        break;
                    }
                }
            }
        }
        return countC;
    }

    public boolean addPrerequiste(String prerequesite, String idCourseToAddPrerequsite) {
        //Course tempCourseP = querySpecificCourse(prerequesite);
        // Course cP = new Course(tempCourseP.getIdCourse(), tempCourseP.getNameCourse(), tempCourseP.getSemestersOffered());
        Course tempCourse = querySpecificCourse(idCourseToAddPrerequsite);
        if (tempCourse != null) {
            this.courses.get(courses.indexOf(tempCourse)).getCoursesPrerequisiteIDs().add(prerequesite);
            return true;
        } else {
            return false;
        }
    }

    public String doAssigments(String idProfessor, String idCourse, int capStudents) {
        String reason = "";
        if (professorsWithCurrentClasses().size() <= 5) {
            
            Course tempCourse = querySpecificCourse(idCourse);
            Professor tempProfe = querySpecificProfessor(idProfessor);
            
            if (tempProfe.getCurrentCourse() == null) {
                for (int i = 0; i < this.courses.size(); i++) {
                    if (this.courses.get(i).getIdCourse().equals(tempCourse.getIdCourse())) {
                        this.courses.get(i).setCapStudents(capStudents);
                        if (tempProfe != null) {
                            ((Professor) (this.getEmployees().get(this.getEmployees().indexOf(tempProfe)))).setCurrentCourse(this.courses.get(i));
                            //tempProfe.setCurrentCourse(this.courses.get(i));                        
                            return reason += "succeded";
                        }
                    }
                }
            } else {
                return reason += "instructor already selected to teach a course";
            }
            return reason += "Error, instructor not found";
        } else {
            return reason += "all instructors selected - no available hiring positions";
        }
    }

    public String handleRequestStudent(String studentID, String courseIDRequested) {

        Course tempC = querySpecificCourse(courseIDRequested);

        Student tempS = querySpecificStudent(studentID);
        Request tempRequest = new Request(tempS, tempC);
        ArrayList<Record> recordsS = queryRecordsFromStudent(studentID);
        // System.out.println("Student: "+studentID+ " "+" coursesSze"+tempC.getStudentsRegistered()+ "tempC N"+tempC.getIdCourse()+" 1- "+courses.get(5).getCoursesPrerequisite().size());
//        System.out.println("Course+ " + tempC.getIdCourse() + "NumStud: " + tempC.getStudentsRegistered() + "get CAp " + tempC.getCapStudents());

        for (int i = 0; i < recordsS.size(); i++) {

            if (querySpecificCourse(recordsS.get(i).getIdCourse()).getIdCourse().equals(tempC.getIdCourse())) {
                if (recordsS.get(i).getPerformace().equalsIgnoreCase("A")
                        || recordsS.get(i).getPerformace().equalsIgnoreCase("B")
                        || recordsS.get(i).getPerformace().equalsIgnoreCase("C")) {
                    tempRequest.setAccepted(false);
                    tempRequest.setReasonNotAccepted("performance");
                    this.requests.add(tempRequest);
                    return "student has already taken the course with a grade of C or higher";
                }

            }
        }

        if (!tempS.meetPrerequisites(tempC, recordsS)) {
            tempRequest.setAccepted(false);
            tempRequest.setReasonNotAccepted("prere");
            this.requests.add(tempRequest);
            return "student is missing one or more prerequisites";

        }
        if (!(tempC.getStudentsRegistered() < tempC.getCapStudents())) {
            tempRequest.setAccepted(false);
            tempRequest.setReasonNotAccepted("cap");
            this.requests.add(tempRequest);
            return "no remaining seats available for the course at this time";
        }
        courses.get(courses.indexOf(tempC)).setStudentsRegistered(courses.get(courses.indexOf(tempC)).getStudentsRegistered() + 1);
        students.get(students.indexOf(tempS)).getCoursesOfStudent().add(tempC);
        // System.out.println("UPDATEDDDD Course+ " + tempC.getIdCourse() + "NumStud: " + tempC.getStudentsRegistered() + "get CAp " + tempC.getCapStudents());
        tempRequest.setAccepted(true);

        this.requests.add(tempRequest);
        return "request is valid";

    }

    public int queryRequestsFailedCap() {
        int counter = 0;
        for (int i = 0; i < this.requests.size(); i++) {
            if (!this.requests.get(i).isAccepted()) {
                if (this.requests.get(i).getReasonNotAccepted().equalsIgnoreCase("cap")) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int queryRequestsFailedPerformance() {
        int counter = 0;
        for (int i = 0; i < this.requests.size(); i++) {
            if (!this.requests.get(i).isAccepted()) {
                if (this.requests.get(i).getReasonNotAccepted().equalsIgnoreCase("performance")) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int queryRequestsFailedPrere() {
        int counter = 0;
        for (int i = 0; i < this.requests.size(); i++) {
            if (!this.requests.get(i).isAccepted()) {
                if (this.requests.get(i).getReasonNotAccepted().equalsIgnoreCase("prere")) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public void addSeats(String courseID, int addSeats) {
        Course temp = querySpecificCourse(courseID);
        courses.get(courses.indexOf(temp)).setCapStudents(courses.get(courses.indexOf(temp)).getCapStudents() + addSeats);
    }

    void addRecord(Record rAux) {
        Student tS = querySpecificStudent(rAux.getIdStudent());
        Course tC = querySpecificCourse(rAux.getIdCourse());
        students.get(students.indexOf(tS)).getCoursesOfStudent().add(tC);
        records.add(rAux);

    }
}

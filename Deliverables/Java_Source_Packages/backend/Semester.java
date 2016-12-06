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
public class Semester {
    private String idSemester;
    private String name;
    private ArrayList<Student> studentsWaitList;
    private String year;

    public String getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(String idSemester) {
        this.idSemester = idSemester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudentsWaitList() {
        return studentsWaitList;
    }

    public void setStudentsWaitList(ArrayList<Student> studentsWaitList) {
        this.studentsWaitList = studentsWaitList;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Semester(String idSemester, String name, ArrayList<Student> studentsWaitList, String year) {
        this.idSemester = idSemester;
        this.name = name;
        this.studentsWaitList = studentsWaitList;
        this.year = year;
    }
    
}

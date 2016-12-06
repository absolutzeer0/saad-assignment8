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
public class Professor extends Employee {

    private Course currentCourse;
    private String extraInfo;
    private ArrayList<String> qualifications;

    public Professor(String idP, String nameP, String addressP, String phoneP) {
        super(idP, nameP, addressP, phoneP);
    }

    public Course getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(Course currentCourse) {
        this.currentCourse = currentCourse;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public ArrayList<String> getQualifications() {
        return qualifications;
    }

    public void setQualifications(ArrayList<String> qualifications) {
        this.qualifications = qualifications;
    }

    public Professor(Course currentCourse, String extraInfo, ArrayList<String> qualifications, String uid, String name, String phoneNumber, String address, String salary) {
        super(uid, name, phoneNumber, address, salary);
        this.currentCourse = currentCourse;
        this.extraInfo = extraInfo;
        this.qualifications = qualifications;
    }

    public Professor(Course currentCourse, String extraInfo, ArrayList<String> qualifications, String uid, String name, String address, String phoneNumber) {
        super(uid, name, address, phoneNumber);
        this.currentCourse = currentCourse;
        this.extraInfo = extraInfo;
        this.qualifications = qualifications;
    }

    public static Professor getProfessorByName(String name, ArrayList<Employee> list)
    {
        for(Employee p : list)
        {
            if(p.getName().matches(name)) return (Professor) p;
        }
        return null;
    }

}

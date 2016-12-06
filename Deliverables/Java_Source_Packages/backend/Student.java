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
public class Student {

    private String uuid;
    private String nameStudent;
    private String address;
    private String phoneNumber;
    private String nameJob;
    private ArrayList<Course> coursesOfStudent;
    private ArrayList<Course> coursesPendingForCounselorApproval;

    public Student(String uuid, String nameStudent, String address, String phoneNumber, String nameJob, ArrayList<Course> coursesOfStudent) {
        this.uuid = uuid;
        this.nameStudent = nameStudent;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nameJob = nameJob;
        this.coursesOfStudent = coursesOfStudent;
    }

    public Student(String uuid, String nameStudent, String address, String phoneNumber) {
        this.uuid = uuid;
        this.nameStudent = nameStudent;
        this.address = address;
        this.phoneNumber = phoneNumber;
        coursesOfStudent = new ArrayList<>();
    }

    public boolean enrollCourse(String idCourse) {
        return true;
    }

    public boolean dropCourse(String idCourse) {
        return true;
    }

    public ArrayList<String> queryPastCourses() {
        return null;
    }

    public ArrayList<String> queryCurrentCourses() {
        return null;
    }

    public boolean updatePersonalInfo(String fieldToUpdate, String newInfo) {
        return true;
    }

    public boolean isCourseInCurrentCatalog(String idCourse) {
        return true;
    }

    public boolean meetPrerequisites(Course course, ArrayList<Record> recordsStudent) {
        ArrayList<String> coursesP = course.getCoursesPrerequisiteIDs();
        int counter = coursesP.size();
        for (int j = 0; j < coursesP.size(); j++) {
            for (int i = 0; i < recordsStudent.size(); i++) {
                if (coursesP.get(j).equalsIgnoreCase(recordsStudent.get(i).getIdCourse())) {
//                    coursesP.remove(j);
                    counter--;
                }
            }

        }
        if (counter > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Student{" + "uuid=" + uuid + ", nameStudent=" + nameStudent + ", address=" + address + ", phoneNumber=" + phoneNumber + "}";
    }

    public String getUuid() {
        return uuid;
    }

    public ArrayList<Course> getCoursesOfStudent() {
        return coursesOfStudent;
    }

    public void setCoursesOfStudent(ArrayList<Course> coursesOfStudent) {
        this.coursesOfStudent = coursesOfStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}

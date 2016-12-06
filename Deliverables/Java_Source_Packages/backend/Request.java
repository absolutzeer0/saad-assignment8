package backend;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jorge
 */
public class Request {

    private Student student;
    private Course courseRequested;
    private boolean accepted;
    private String reasonNotAccepted;

    public Request(Student student, Course courseRequested) {
        this.student = student;
        this.courseRequested = courseRequested;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourseRequested() {
        return courseRequested;
    }

    public void setCourseRequested(Course courseRequested) {
        this.courseRequested = courseRequested;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getReasonNotAccepted() {
        return reasonNotAccepted;
    }

    public void setReasonNotAccepted(String reasonNotAccepted) {
        this.reasonNotAccepted = reasonNotAccepted;
    }

    @Override
    public String toString() {
        return "Request{" + "student=" + student + ", courseRequested=" + courseRequested + ", accepted=" + accepted + ", reasonNotAccepted=" + reasonNotAccepted + '}';
    }

}

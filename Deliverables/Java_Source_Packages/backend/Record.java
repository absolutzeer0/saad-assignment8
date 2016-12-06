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
public class Record {

    private String idStudent;
    private String idCourse;
    private String idProfessor;
    private String commentsProfessor;
    private String performace;

    public Record(String idStudent, String idCourse, String idProfessor, String commentsProfessor, String performace) {
        this.idStudent = idStudent;
        this.idCourse = idCourse;
        this.idProfessor = idProfessor;
        this.commentsProfessor = commentsProfessor;
        this.performace = performace;
    }

    @Override
    public String toString() {
        return "Record{" + "idStudent=" + idStudent + ", idCourse=" + idCourse + ", idProfessor=" + idProfessor + ", commentsProfessor=" + commentsProfessor + ", performace=" + performace + '}';
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getCommentsProfessor() {
        return commentsProfessor;
    }

    public void setCommentsProfessor(String commentsProfessor) {
        this.commentsProfessor = commentsProfessor;
    }

    public String getPerformace() {
        return performace;
    }

    public void setPerformace(String performace) {
        this.performace = performace;
    }

}

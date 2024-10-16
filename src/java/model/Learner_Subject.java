/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Learner_Subject {
    private int id;
    private int learner_id;
    private int subject_id;
    private String enrolled_date;
    private String end_date;
    private int active;
    private int status;
    private int account_id;
    private String fullname;
    private String subject_name;
    private String image;
    private int lecturer_id;
    private String lecturer_name;
    private int category_id;
    private String enroll_date;

    public String getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(String enroll_date) {
        this.enroll_date = enroll_date;
    }
    

    public Learner_Subject() {
    }

    public String getEnrolled_date() {
        return enrolled_date;
    }

    public void setEnrolled_date(String enrolled_date) {
        this.enrolled_date = enrolled_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Learner_Subject(int id, int learner_id, int subject_id, String enrolled_date, String end_date, int active, int status, int account_id, String fullname, String subject_name, String image, int lecturer_id, String lecturer_name, int category_id) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enrolled_date = enrolled_date;
        this.end_date = end_date;
        this.active = active;
        this.status = status;
        this.account_id = account_id;
        this.fullname = fullname;
        this.subject_name = subject_name;
        this.image = image;
        this.lecturer_id = lecturer_id;
        this.lecturer_name = lecturer_name;
        this.category_id = category_id;
    }

    public Learner_Subject(int id, int learner_id, int subject_id, String enroll_date, String end_date, int active, int status) {
        this.id = id;
        this.learner_id = learner_id;
        this.subject_id = subject_id;
        this.enroll_date = enroll_date;
        this.end_date = end_date;
        this.active = active;
        this.status = status;
    }

    

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearner_id() {
        return learner_id;
    }

    public void setLearner_id(int learner_id) {
        this.learner_id = learner_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    @Override
    public String toString() {
        return "Learner_Subject{" + "id=" + id + ", learner_id=" + learner_id + ", subject_id=" + subject_id + ", enrolled_date=" + enrolled_date + ", end_date=" + end_date + ", active=" + active + ", status=" + status + ", account_id=" + account_id + ", fullname=" + fullname + ", subject_name=" + subject_name + ", image=" + image + ", lecturer_id=" + lecturer_id + ", lecturer_name=" + lecturer_name + ", category_id=" + category_id + '}';
    }
    

    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Course {

   private int course_id;
   private String course_name;
   private String image;
   private int subject_id;
   private int course_no;
   private String description;
   private int chapter_num;
   private int lesson_num;
   private String category_name;
   private double rate_course;

    public Course() {
    }

    public Course(int course_id, String course_name, String image, int subject_id, int course_no, String description, int chapter_num, int lesson_num, String category_name, double rate_course) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.image = image;
        this.subject_id = subject_id;
        this.course_no = course_no;
        this.description = description;
        this.chapter_num = chapter_num;
        this.lesson_num = lesson_num;
        this.category_name = category_name;
        this.rate_course = rate_course;
    }
    public Course(int course_id, String course_name, String image, int subject_id, int course_no) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.image = image;
        this.subject_id = subject_id;
        this.course_no = course_no;
    }


    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getCourse_no() {
        return course_no;
    }

    public void setCourse_no(int course_no) {
        this.course_no = course_no;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChapter_num() {
        return chapter_num;
    }

    public void setChapter_num(int chapter_num) {
        this.chapter_num = chapter_num;
    }

    public int getLesson_num() {
        return lesson_num;
    }

    public void setLesson_num(int lesson_num) {
        this.lesson_num = lesson_num;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public double getRate_course() {
        return rate_course;
    }

    public void setRate_course(double rate_course) {
        this.rate_course = rate_course;
    }

    
    

    
   

}

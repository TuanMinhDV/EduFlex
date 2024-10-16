/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Subject {

    private int subject_id;
    private String subject_name;
    private String description;
    private String image;
    private float price;
    private float discount;
    private int sold;
    private String created_date;
    private String updated_date;
    private int category_id;
    private int lecturer_id;
    private String lecturer_name;
    private double rate_subject;

    public Subject() {
    }

    public Subject(int subject_id, String subject_name, String description, String image, float price, float discount, int sold, String created_date, String updated_date, int category_id, int lecturer_id, String lecturer_name, double rate_subject) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.sold = sold;
        this.created_date = created_date;
        this.updated_date = updated_date;
        this.category_id = category_id;
        this.lecturer_id = lecturer_id;
        this.lecturer_name = lecturer_name;
        this.rate_subject = rate_subject;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public double getRate_subject() {
        return rate_subject;
    }

    public void setRate_subject(double rate_subject) {
        this.rate_subject = rate_subject;
    }

    
    
    

    

    

}

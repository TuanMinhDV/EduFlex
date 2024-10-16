/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class OrderSubject {
    private int subject_id;
    private String subject_name;
    private String image;
    private double price;
    private int category_id;
    private double discount;

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    

    public OrderSubject(int subject_id, String subject_name, String image, double price, int category_id, double discount) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.image = image;
        this.price = price;
        this.category_id = category_id;
        this.discount = discount;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public OrderSubject(int subject_id, String subject_name, String image, double price, int category_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.image = image;
        this.price = price;
        this.category_id = category_id;
    }

    public OrderSubject() {
    }

    public OrderSubject(int subject_id, String subject_name, String image, double price) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.image = image;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
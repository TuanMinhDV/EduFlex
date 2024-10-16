package model;

public class Item {

    private OrderSubject orderSubject;
    private double price;
    private Subject subject;
    private int category_id;
    private double discount;

    public Item(OrderSubject orderSubject, double price, Subject subject, int category_id, double discount) {
        this.orderSubject = orderSubject;
        this.price = price;
        this.subject = subject;
        this.category_id = category_id;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    

    public Item(OrderSubject orderSubject, double price, Subject subject, int category_id) {
        this.orderSubject = orderSubject;
        this.price = price;
        this.subject = subject;
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }


    public Item() {
    }

    public Item(Subject subject, double price) {
        this.subject = subject;
        this.price = price;
    }

    public Item(OrderSubject orderSubject, double price) {
        this.orderSubject = orderSubject;
        this.price = price;
    }

    public OrderSubject getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(OrderSubject orderSubject) {
        this.orderSubject = orderSubject;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}

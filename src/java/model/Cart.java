
package model;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    private Item getItemsBySubjectId(int subject_id){
        for (Item i : items) {
            if(i.getOrderSubject().getSubject_id()==subject_id)
                return i;
        }
        return null;
        
    }
    public void addItem(Item t){
        if(getItemsBySubjectId(t.getOrderSubject().getSubject_id())!=null){
            Item m =getItemsBySubjectId(t.getOrderSubject().getSubject_id());
        }else{
            items.add(t);
        }
    }
    public void removeItem(int subject_id){
        if(getItemsBySubjectId(subject_id)!=null){
            items.remove(getItemsBySubjectId(subject_id));
        }
    }
    public double getTotalMoney(){
        double t = 0;
        for (Item i : items) {
            t+=i.getPrice();
        }
            return t;  
    }
     public void clearCart() {
        items.clear();
    }
 

//    public static void main(String[] args) {
//        Cart cart = new Cart();
//        Item i = new Item(new OrderSubject(1, "", "", 0.0), 0);
//        Item i2 = new Item(new OrderSubject(2, "", "", 0.0), 0);
//        cart.addItem(i);
//        cart.addItem(i2);
//        for (Item item : cart.items) {
//            System.out.println(item.getOrderSubject().getSubject_id());
//            
//        }
//        cart.removeItem(1);
//        for (Item item : cart.items) {
//            System.out.println(item.getOrderSubject().getSubject_id());
//            
//        }
//    }
}
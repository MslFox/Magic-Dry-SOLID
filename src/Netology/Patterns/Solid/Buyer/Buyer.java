package Netology.Patterns.Solid.Buyer;

import Netology.Patterns.Solid.Order.Order;
import Netology.Patterns.Solid.Shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private String name;
    private String email;
    private List<Order> buyerOrders = new ArrayList<>();

    public Buyer(String name, String email) {
        this.name = name;
        this.email = email;
   }

    public String getName() {
        return name;
    }

    private Order createOrder(Shop shop) {

        return new Order(this, shop);
    }

    private void removeOrder(Shop shop) {

    }

    private void addToOrder(Order order) {
    }

    private void removeFromOrder(Order order) {
    }

    @Override
    public String toString() {
        return "Покупатель: " + name + "   Email: " + email;
    }
}

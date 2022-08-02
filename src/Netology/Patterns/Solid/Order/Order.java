package Netology.Patterns.Solid.Order;

import Netology.Patterns.Solid.Buyer.Buyer;
import Netology.Patterns.Solid.Shop.ProductShop;
import Netology.Patterns.Solid.Shop.Columns;
import Netology.Patterns.Solid.Shop.Shop;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final Buyer buyer;
    private final Shop shop;
    List<OrderItem> orderItems = new ArrayList<>();

    private double orderFullPrice = 0;
    private OrderStatus orderStatus = OrderStatus.NotPAYED;
    private DeliveryStatus deliveryStatus;

    public Order(Buyer buyer, Shop shop) {
        this.buyer = buyer;
        this.shop = shop;
        deliveryStatus = DeliveryStatus.DISABLE;
    }

    public List<OrderItem> getOrderList() {
        return orderItems;
    }

    public void addToOrder(ProductShop productShop, int valueProduct) {
        if (productShop.getStockValue() < valueProduct) {
            System.out.println("Недостаточно товара на складе!");
            return;
        }

        OrderItem currentOrderItem = getOrderItem(productShop);
        if (currentOrderItem != null) {
            currentOrderItem.setProductValue(currentOrderItem.getProductValue() + valueProduct);
        } else {
            orderItems.add(new OrderItem(productShop, valueProduct));
        }
        shop.setProductValue(productShop, productShop.getStockValue() - valueProduct);
        orderFullPrice += productShop.getPrice() * valueProduct;
        System.out.printf("%s в количестве %s ед. добавлен в корзину.\n", productShop, valueProduct);
    }

    public void removeFromOrder(OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public double getOrderFullPrice() {
        return orderFullPrice;
    }

    public void setOrderFullPrice(double orderFullPrice) {
        this.orderFullPrice = orderFullPrice;
    }

    public OrderItem getOrderItem(ProductShop productShop) {
        if (!orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getProduct().equals(productShop)) return orderItem;
            }
        }
        return null;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        if (orderStatus == OrderStatus.PAYED) {
            Thread delivery = new Delivery(this);
            delivery.start();
        }
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    private String printHead() {
        String printFormatString = "" +
                " %" + shop.numberLengthMax + "s" +
                " | %" + shop.nameLengthMax + "s" +
                " | %" + (shop.manufactureLengthMax + shop.SEPARATOR) + "s" +
                " | %" + (Columns.PRICE.getName().length() + shop.SEPARATOR) + "s" +
                " | %" + (Columns.ORDEREDVALUE.getName().length() + shop.SEPARATOR) + "s" +
                " | %" + (Columns.ORDEREDPRICE.getName().length() + shop.SEPARATOR) + "s |" + "\n";

        System.out.printf(printFormatString,
                Columns.NUMBER.getName(),
                Columns.NAME.getName(),
                Columns.MANUFACTURE.getName(),
                Columns.PRICE.getName(),
                Columns.ORDEREDVALUE.getName(),
                Columns.ORDEREDPRICE.getName());
        return printFormatString;
    }

    public void printOrder() {
        if (orderItems.isEmpty()) {
            System.out.println("Корзина пуста.");
            return;
        }
        String printFormatString = printHead();
        int count = 1;
        for (OrderItem orderItem : orderItems) {
            System.out.printf(printFormatString,
                    count++,
                    orderItem.getProduct().getName(),
                    orderItem.getProduct().getManufacture(),
                    orderItem.getProduct().getPrice(),
                    orderItem.getProductValue(),
                    orderItem.getProduct().getPrice() * orderItem.getProductValue());
        }
        System.out.println(
                Columns.FULLORDEREDPRICE.getName() + orderFullPrice + " руб.\n" +
                        Columns.ORDERSTATUS.getName() + orderStatus.getOrderStatus() + "\n" +
                        Columns.DELIVERYSTATUS.getName() + deliveryStatus.getDeliveryStatus());
    }
}


package Netology.Patterns.Solid.Order;

public class Delivery extends Thread {
    private final int time = 15000;
    private Order order;

    public Delivery(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        deliverySteps();

    }

    public void deliverySteps() {
        try {

            order.setDeliveryStatus(DeliveryStatus.UNBOXED);
            Thread.sleep(time);
            order.setDeliveryStatus(DeliveryStatus.BOXED);
            Thread.sleep(time);
            order.setDeliveryStatus(DeliveryStatus.SENT);
            Thread.sleep(time);
            order.setDeliveryStatus(DeliveryStatus.ONTHEWAY);
            Thread.sleep(time);
            order.setDeliveryStatus(DeliveryStatus.DELIVERED);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}

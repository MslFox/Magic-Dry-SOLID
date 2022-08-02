package Netology.Patterns.Solid.Order;


import java.time.LocalDateTime;

public class ArchiveOrder {
    private Order order;
    private LocalDateTime dateTime;

    public ArchiveOrder(Order order) {
        this.order = order;
        this.dateTime = LocalDateTime.now();
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

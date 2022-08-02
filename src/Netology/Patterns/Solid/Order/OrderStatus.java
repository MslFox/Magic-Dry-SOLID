package Netology.Patterns.Solid.Order;

public enum OrderStatus {
    PAYED("Оплачен"),
    NotPAYED("Ожидает оплаты");

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}

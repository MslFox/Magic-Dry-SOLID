package Netology.Patterns.Solid.Order;

public enum DeliveryStatus {
    DISABLE("После оплаты"),
    BOXED("Собран"),
    UNBOXED("В процессе сборки"),
    SENT("Отправлен"),
    DELIVERED("Доставлен"),
    ONTHEWAY("В пути");

    private final String deliveryStatus;

    DeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}

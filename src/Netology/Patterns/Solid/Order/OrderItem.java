package Netology.Patterns.Solid.Order;

import Netology.Patterns.Solid.Shop.ProductShop;

public class OrderItem {
    private ProductShop productShop;
    private int productValue;

    public OrderItem(ProductShop productShop, int productValue) {
        this.productShop = productShop;
        this.productValue = productValue;
    }

    public ProductShop getProduct() {
        return productShop;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }
}

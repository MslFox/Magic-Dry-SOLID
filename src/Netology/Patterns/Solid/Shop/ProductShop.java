package Netology.Patterns.Solid.Shop;

public class ProductShop extends Product implements Comparable<ProductShop> {
    private int stockValue;
    private final double rating;
    private double price;

    public ProductShop(String name, ProductGroup productGroup, String manufacture, double price, int stockValue, double rating) {
        super(name, productGroup, manufacture);
        this.stockValue = stockValue;
        this.rating = rating;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getStockValue() {
        return stockValue;
    }

    protected double getRating() {
        return rating;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    protected void setStockValue(int stockValue) {
        this.stockValue = stockValue;
    }

    @Override
    public int compareTo(ProductShop o) {
        if (getProductGroup().getName().compareTo(o.getProductGroup().getName()) != 0)
            return getProductGroup().getName().compareTo(o.getProductGroup().getName());
        if (getName().compareTo(o.getName()) != 0)
            return getName().compareTo(o.getName());
        return getManufacture().compareTo(o.getManufacture());
    }
}






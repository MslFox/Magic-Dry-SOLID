package Netology.Patterns.Solid.Shop;

public class Product implements Manufacture, Group {
    private final String name;
    private final ProductGroup productGroup;
    private final String manufacture;

    public Product(String name, ProductGroup productGroup, String manufacture) {
        this.name = name;
        this.productGroup = productGroup;
        this.manufacture = manufacture;
    }
    public String getName() {
        return name;
    }
    @Override
    public ProductGroup getProductGroup() {
        return productGroup;
    }
    @Override
    public String getManufacture() {
        return manufacture;
    }
    @Override
    public String toString() {
        return name;
    }

}

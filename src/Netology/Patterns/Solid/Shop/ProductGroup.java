package Netology.Patterns.Solid.Shop;

public enum ProductGroup {
    SAUSAGE("Колбасные изделия"),
    BREAD("Хлебобулочные изделия"),
    CONFECTIONARY("Кондитерские изделия");
    private String group;

    ProductGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return group;
    }
}

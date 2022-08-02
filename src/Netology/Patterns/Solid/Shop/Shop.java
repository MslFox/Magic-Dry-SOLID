package Netology.Patterns.Solid.Shop;
import Netology.Patterns.Solid.Order.ArchiveOrder;
import Netology.Patterns.Solid.Order.Order;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    public final int SEPARATOR = 3;
    private final String name;
    private final List<ArchiveOrder> archiveOrders = new ArrayList<>();
    private final List<ProductShop> productShopList = new ArrayList<>();

    public int numberLengthMax = Columns.NUMBER.getName().length();
    public int nameLengthMax = Columns.NAME.getName().length();
    public int groupLengthMax = Columns.GROUP.getName().length();
    public int manufactureLengthMax = Columns.MANUFACTURE.getName().length();

    private String printFormatString;


    public Shop(String name) {
        this.name = name;
        addProductToShop(new ProductShop("Конфеты «Буратино»", ProductGroup.CONFECTIONARY,
                "ООО РотФронт", 10, 170, 10.5));
        addProductToShop(new ProductShop("Торт «Гуливер»", ProductGroup.CONFECTIONARY,
                "Красный Октябрь", 10, 110, 10.5));
        addProductToShop(new ProductShop("Плюшка «Московская»", ProductGroup.BREAD,
                "ООО «Злаки»", 5, 15, 2.2));
        addProductToShop(new ProductShop("Булка «Домашняя»", ProductGroup.BREAD,
                "ИП Бабушкин»", 5, 15, 2.3));
        addProductToShop(new ProductShop("Хлеб «Бородинский»", ProductGroup.BREAD,
                "Хлебзавод ЗерноХлеб", 5, 15, 3.4));
        addProductToShop(new ProductShop("Хлеб «Бородинский»", ProductGroup.BREAD,
                "Хлебзавод №4", 4, 20, 5.5));
        addProductToShop(new ProductShop("Колбаса «Салями»", ProductGroup.SAUSAGE,
                "ООО Дымоф", 6, 15, 2.2));
        addProductToShop(new ProductShop("Колбаса «Халяль»", ProductGroup.SAUSAGE,
                "ИП Хачаттурян»", 5, 15, 2.2));
        addProductToShop(new ProductShop("Колбаса «Докторская»", ProductGroup.SAUSAGE,
                "Мясокомбинат  «Бекон» ",
                5, 15, 2.2));
    }

    public String getName() {
        return name;
    }

    public List<ProductShop> getProductList() {
        return productShopList.stream().sorted().toList();
    }


    public void addProductToShop(ProductShop productShop) {
        productShopList.add(productShop);
        if ((productShopList.size() + "").length() > numberLengthMax) {
            numberLengthMax = (productShopList.size() + "").length();
        }
        if (nameLengthMax < productShop.getName().length()) {
            nameLengthMax = productShop.getName().length();
        }
        if (groupLengthMax < productShop.getProductGroup().getName().length()) {
            groupLengthMax = productShop.getProductGroup().getName().length();
        }

        if (manufactureLengthMax < productShop.getManufacture().length()) {
            manufactureLengthMax = productShop.getManufacture().length();
        }
    }
    public void addOrderToArchive(Order order){
        archiveOrders.add(new ArchiveOrder(order));
    }

    public List<ArchiveOrder> getArchiveOrders() {
        return archiveOrders;
    }

    private void printHead() {
        printFormatString = "" +
                " %" + numberLengthMax + "s" +
                " | %" + nameLengthMax + "s" +
                " | %" + (groupLengthMax + SEPARATOR) + "s" +
                " | %" + (manufactureLengthMax + SEPARATOR) + "s" +
                " | %" + (Columns.PRICE.getName().length() + SEPARATOR) + "s" +
                " | %" + (Columns.STOCKROOMVALUE.getName().length() + SEPARATOR) + "s" +
                " | %" + (Columns.RATING.getName().length() + SEPARATOR) + "s |" + "\n";

        System.out.printf(printFormatString,
                Columns.NUMBER.getName(),
                Columns.NAME.getName(),
                Columns.GROUP.getName(),
                Columns.MANUFACTURE.getName(),
                Columns.PRICE.getName(),
                Columns.STOCKROOMVALUE.getName(),
                Columns.RATING.getName());
    }

    public void setProductValue(ProductShop productShop, int value) {
        productShop.setStockValue(value);
    }

    public void printProducts(List<ProductShop> productShops) {
        printHead();
        int count = 1;
        for (ProductShop productShop : productShops) {
            System.out.printf(printFormatString,
                    count++,
                    productShop.getName(),
                    productShop.getProductGroup().getName(),
                    productShop.getManufacture(),
                    productShop.getPrice(),
                    productShop.getStockValue(),
                    productShop.getRating());
        }
    }
}
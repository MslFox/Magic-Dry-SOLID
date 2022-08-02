package Netology.Patterns.Solid.MenuDialog;

import Netology.Patterns.Solid.Buyer.Buyer;
import Netology.Patterns.Solid.Order.Order;
import Netology.Patterns.Solid.Order.OrderStatus;
import Netology.Patterns.Solid.Shop.ProductShop;
import Netology.Patterns.Solid.Shop.Columns;
import Netology.Patterns.Solid.Shop.Shop;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Dialog {
    private static final Scanner scanner = new Scanner(System.in);
    private final Shop shop;
    private final Buyer buyer;
    private final Menu mainMenu;
    private final Menu searchMenu;
    private final Menu basketMenu;
    private final Menu archiveOrderMenu;
    private Order order;
    private List<ProductShop> currentProductListShop;


    public Dialog(Shop shop, Buyer buyer) {
        this.shop = shop;
        this.buyer = buyer;
        mainMenu = new Menu("\nОСНОВНОЕ МЕНЮ " + shop.getName() + "\n");
        searchMenu = new Menu("\nМЕНЮ ПОИСКА " + shop.getName() + "\n");
        basketMenu = new Menu("\nМЕНЮ КОРЗИНЫ " + shop.getName() + "\n");
        archiveOrderMenu = new Menu("\nМЕНЮ АРХИВНЫХ ЗАКАЗОВ " + shop.getName() + "\n");
        order = new Order(buyer, shop);
    }

    private void setMenus() {

        mainMenu.
                addMenuItem(new MenuItem("Посмотреть полный список товаров.",
                        () -> {
                            currentProductListShop = shop.getProductList();
                            shop.printProducts(currentProductListShop);
                            mainMenu.menuChoice();
                        })).
                addMenuItem(new MenuItem("Поиск товаров",
                        searchMenu::menuChoice)).
                addMenuItem(new MenuItem("Добавить товар в корзину ( из списка выше) .",
                        () -> {
                            addToBasket();
                            mainMenu.menuChoice();
                        })).
                addMenuItem(new MenuItem("Посмотреть корзину.", () -> {
                    System.out.println(buyer.getName() + "! Ваша корзина.");
                    order.printOrder();
                    mainMenu.menuChoice();
                })).
                addMenuItem(new MenuItem("Оплатить/Редактировать корзину.",
                        () -> {
                            if (order.getOrderList().isEmpty()) {
                                System.out.println("Корзина пуста!");
                                mainMenu.menuChoice();
                            }
                            order.printOrder();
                            basketMenu.menuChoice();
                        })).
                addMenuItem(new MenuItem("Посмотреть архив заказов.",
                        () -> {
                            if (shop.getArchiveOrders().isEmpty()) {
                                System.out.println("В архиве нет заказов!");
                                mainMenu.menuChoice();
                            } else {
                                shop.getArchiveOrders().forEach(x -> {
                                    System.out.println("Заказ от " + x.getDateTime().
                                            format(DateTimeFormatter.ofPattern("dd/MM/YY HH:mm")));
                                    x.getOrder().printOrder();
                                });
                                mainMenu.menuChoice();
                            }
                        })).
                addMenuItem(new MenuItem("Выйти из магазина.",
                        () -> {
                            System.out.println(buyer.getName() + ", досвидания! Приходите еще!");
                            return;
                        }));

        searchMenu.
                addMenuItem(new MenuItem("Поиск по ключевому слову (название продукта)",
                        () -> searchBy(Columns.NAME))).
                addMenuItem(new MenuItem("Поиск по цене",
                        () -> searchBy(Columns.PRICE))).
                addMenuItem(new MenuItem("Поиск по производителю",
                        () -> searchBy(Columns.MANUFACTURE))).
                addMenuItem(new MenuItem("Вернуться в главное меню",
                        mainMenu::menuChoice));

        basketMenu.
                addMenuItem(new MenuItem("Удалить товар из корзины",
                        () -> {
                            removeProductFromOrder();
                            basketMenu.menuChoice();
                        })).
                addMenuItem(new MenuItem("Оплатить корзину.",
                        () -> {
                            order.setOrderStatus(OrderStatus.PAYED);
                            System.out.println("Заказ оплачен! Заказ перемещен в раздел  \"Архив заказов\"");
                            shop.addOrderToArchive(order);
                            order = new Order(buyer, shop);
                            mainMenu.menuChoice();
                        })).
                addMenuItem(new MenuItem("Посмотреть корзину.", () -> {
                    System.out.println(buyer.getName() + "! Ваша корзина.");
                    order.printOrder();
                    basketMenu.menuChoice();
                    })).
                addMenuItem(new MenuItem("Вернуться в основное меню",
                        mainMenu::menuChoice));
    }

    public void startDialog() {
        System.out.println("\n" + buyer.getName() + "! Добро пожаловать в " + shop.getName());
        setMenus();
        currentProductListShop = shop.getProductList();
        shop.printProducts(currentProductListShop);
        mainMenu.menuChoice();
    }

    private void addToBasket() {
        scanner.reset();
        System.out.println("Для добавления товара в корзину, введите через пробел номер товара и количество (например 2 1)" );
        try {
            int productNumber = scanner.nextInt();
            int productValue = scanner.nextInt();
            order.addToOrder(currentProductListShop.get(productNumber - 1), productValue);
        } catch (RuntimeException e) {
            System.out.println("Введите корректный номер товара!");
            scanner.nextLine();
            addToBasket();
        }
    }

    private void removeProductFromOrder() {
        scanner.reset();
        System.out.println(buyer.getName() + "! Ваша корзина:");
        order.printOrder();
        if (order.getOrderList().isEmpty()) return;
        System.out.println("Введите номер товара для удаления ");
        try {
            int productNumber = scanner.nextInt();
            order.setOrderFullPrice(order.getOrderFullPrice() -
                    order.getOrderList().get(productNumber - 1).getProductValue() *
                    order.getOrderList().get(productNumber - 1).getProduct().getPrice());
            order.removeFromOrder(order.getOrderList().get(productNumber - 1));
        } catch (RuntimeException e) {
            System.out.println("введите корректный номер товара!");
            scanner.nextLine();
            removeProductFromOrder();
        }
    }

    private void searchBy(Columns columns) {
        switch (columns) {
            case NAME -> {
                System.out.println("Введите ключевое слово из названия товара");
                String searchKey = scanner.nextLine();
                currentProductListShop = shop.getProductList().
                        stream().
                        filter(x -> x.getName().toLowerCase().contains(searchKey.toLowerCase())).
                        toList();
            }
            case PRICE -> {
                System.out.println("Введите цену товара");
                try {
                    double searchPrice = Double.parseDouble(scanner.nextLine());
                    currentProductListShop = shop.getProductList().
                            stream().
                            filter(x -> x.getPrice() == searchPrice).
                            toList();
                } catch (NumberFormatException e) {
                    System.out.println("Введите кооректные данные!");
                    searchBy(Columns.PRICE);
                }
            }
            case MANUFACTURE -> {
                System.out.println("Введите ключевое слово из наименования производителя");
                String searchKey = scanner.nextLine();
                currentProductListShop = shop.getProductList().
                        stream().
                        filter(x -> x.getManufacture().toLowerCase().contains(searchKey.toLowerCase())).
                        toList();
            }
        }
        if (currentProductListShop.isEmpty()) System.out.println("Товар не обнаружен!");
        else shop.printProducts(currentProductListShop);
        mainMenu.menuChoice();
    }

    public List<ProductShop> getCurrentProductList() {
        return currentProductListShop;
    }
}


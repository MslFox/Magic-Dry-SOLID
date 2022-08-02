package Netology.Patterns.Solid.Shop;

public enum Columns {
    NUMBER("№"),
    NAME("НАИМЕНОВАНИЕ ТОВАРА"),
    MANUFACTURE("ПРОИЗВОДИТЕЛЬ"),
    GROUP("ГРУППА ТОВАРОВ"),
    PRICE("СТОИМОСТЬ,руб"),
    STOCKROOMVALUE("КОЛИЧЕСТВО НА СКЛАДЕ,ед"),
    RATING("РЕЙТИНГ"),
    ORDEREDVALUE("КОЛИЧЕСТВО в ЗАКАЗЕ,ед"),
    ORDEREDPRICE("ОБЩАЯ СТОИМОСТЬ,руб:"),
    FULLORDEREDPRICE("ИТОГО:"),
    ORDERSTATUS("СТАТУС ОПЛАТЫ: "),
    DELIVERYSTATUS("СТАТУС ДОСТАВИК: ");

    private String name;

    Columns(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


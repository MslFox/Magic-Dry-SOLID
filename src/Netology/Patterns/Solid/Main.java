package Netology.Patterns.Solid;

import Netology.Patterns.Solid.Buyer.Buyer;
import Netology.Patterns.Solid.MenuDialog.Dialog;
import Netology.Patterns.Solid.Shop.Shop;

public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop("Супермаркет \"Шестерочка\"");
        Buyer buyer = new Buyer("Иван", "byer@byer.com");

        Dialog dialog = new Dialog(shop, buyer);

        dialog.startDialog();
    }
}

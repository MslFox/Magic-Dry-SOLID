# Magic-Dry-SOLID

[https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/cec413e617a5d911e160e9817711db703dc91613/src/Netology/Patterns/Solid/Shop/Shop.java#L9](https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/cec413e617a5d911e160e9817711db703dc91613/src/Netology/Patterns/Solid/Shop/Shop.java#L9)

константа служит для отказа от `магических чисел` при построении списка товаров.

[https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/cec413e617a5d911e160e9817711db703dc91613/src/Netology/Patterns/Solid/MenuDialog/Dialog.java#L160](https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/cec413e617a5d911e160e9817711db703dc91613/src/Netology/Patterns/Solid/MenuDialog/Dialog.java#L160)

метод `searchBy` принимает параметром синглтон объект, на основе которого классифицирует типа поиска, вместо нескольких методов типов поиска. Так я применил `DRY`

https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/d1d5d820a13830e5faf8b257d803871a53efc564/src/Netology/Patterns/Solid/Shop/Product.java#L3

Класс `Product` содержит в себе инфу о продукте(цена,производитель, группа товаров) все поля приватные, объект класса имеет только геттеы,сеттеры. 
Больше ничего логически он делать не должен. `(Single Responsibility Principle)`

https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/d1d5d820a13830e5faf8b257d803871a53efc564/src/Netology/Patterns/Solid/Shop/ProductShop.java#L3

Класс `ProductShop` наследуется от `Product`, но уже имплементирует `Comparable` -  умеет сравнивать свои обЪекты.Класс `ProductShop` расширил 
функционал базового класса  `Product`.  `(Open-closed principle)`. 
Также здесь можно говорить и о принципе `(Liskov substitution principle)` наследник играет роль предка.
https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/d1d5d820a13830e5faf8b257d803871a53efc564/src/Netology/Patterns/Solid/Shop/Product.java#L3
Класс `Product` имплементирует два интерфейса  `Manufacture, Group` ( простые геттеры), которые дают понять юзеру интерфейсов, что объект класса должен содержать 
информацию о производителе и группе товаров соответственно. Это мое применение шаблона `(Interface segregation principle)`. 

https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/d1d5d820a13830e5faf8b257d803871a53efc564/src/Netology/Patterns/Solid/MenuDialog/Command.java#L3
Также я использовал интерфейс `Command` шаблона `(Command)` при создании обЪектов класса `MenuItem`
https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/650227cd52881e1804db3b24f23128acdf73f1b1/src/Netology/Patterns/Solid/MenuDialog/MenuItem.java#L7
https://github.com/MsLFoxGit/Magic-Dry-SOLID/blob/650227cd52881e1804db3b24f23128acdf73f1b1/src/Netology/Patterns/Solid/MenuDialog/Dialog.java#L39
```
private void setMenus() {

        mainMenu.
                addMenuItem(new MenuItem("Посмотреть полный список товаров.",
                        () -> {
                            currentProductList = shop.getProductList();
                            shop.printProducts(currentProductList);
                            mainMenu.menuChoice();
                        })).
```

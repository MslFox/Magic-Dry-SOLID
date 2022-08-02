package Netology.Patterns.Solid.MenuDialog;

public class MenuItem {
    public String menuItemName;
    public Command command;

    public MenuItem(String menuItemName, Command command) {
        this.menuItemName = menuItemName;
        this.command = command;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void execute() {
        command.execute();
    }
}

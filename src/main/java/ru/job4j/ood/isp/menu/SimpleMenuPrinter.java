package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int nestingLevel = menuItemInfo.getNumber().split("\\.").length;
            StringBuilder builder = new StringBuilder();
            if (nestingLevel > 1) {
                for (int i = 0; i < Math.pow(nestingLevel, 2); i++) {
                    builder.append("-");
                }
            }
            builder.append(menuItemInfo.getNumber()).append(menuItemInfo.getName());
            System.out.println(builder);
        }
    }
}

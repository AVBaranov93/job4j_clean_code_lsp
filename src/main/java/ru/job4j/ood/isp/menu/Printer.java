package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        System.out.println(createString(menu));
    }

    public String getMenu(Menu menu) {
        return createString(menu);
    }

    private String createString(Menu menu) {
        StringBuilder finalStr = new StringBuilder();
        String separator = System.lineSeparator();
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int nestingLevel = menuItemInfo.getNumber().split("\\.").length;
            StringBuilder builder = new StringBuilder();
            if (nestingLevel > 1) {
                for (int i = 0; i < Math.pow(nestingLevel, 2); i++) {
                    builder.append("-");
                }
            }
            builder.append(menuItemInfo.getNumber()).append(menuItemInfo.getName());
            finalStr.append(builder).append(separator);
        }
        return finalStr.toString();
    }
}

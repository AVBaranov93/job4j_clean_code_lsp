package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    void whenThen() {
        Menu menu = new SimpleMenu();
        String separator = System.lineSeparator();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String expected = "1.Сходить в магазин"
                + separator
                + "----1.1.Купить продукты"
                + separator
                + "---------1.1.1.Купить хлеб"
                + separator
                + "---------1.1.2.Купить молоко"
                + separator;
        assertThat(new Printer().getMenu(menu))
                .isEqualTo(expected);
    }
}
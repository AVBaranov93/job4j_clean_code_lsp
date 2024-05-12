package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean isPresent = false;
        if (parentName == null) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            isPresent = true;
        } else if (findItem(parentName).isPresent()) {
            findItem(parentName).get().menuItem.getChildren().add(new SimpleMenuItem(childName, actionDelegate));
        }
        return isPresent;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        var item = findItem(itemName);
        return Optional.of(new Menu.MenuItemInfo(item.get().menuItem, item.get().number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new MenuItemIterator();
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo itemInfo = null;
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (name.equals(next.menuItem.getName())) {
                itemInfo = next;
            }
        }
        return Optional.ofNullable(itemInfo);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        private Deque<MenuItem> stack = new LinkedList<>();

        private Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class MenuItemIterator implements Iterator<Menu.MenuItemInfo> {
        private DFSIterator dfsIterator = new DFSIterator();

        @Override
        public boolean hasNext() {
            return dfsIterator.hasNext();
        }

        @Override
        public MenuItemInfo next() {
            ItemInfo itemInfo = dfsIterator.next();
            return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
        }
    }

    private class ItemInfo {

        private MenuItem menuItem;
        private String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
package ru.job4j.applications;

public class StubInput implements Input {

    private String[] params;
    private String choiceOfMenu;

    StubInput(String[] parametersOfInput) {
        this.params = parametersOfInput;
        this.choiceOfMenu = params[0];
    }

    public String showMenu() {
        return this.choiceOfMenu;
    }

    /**
     * Добавить item.
     */
    public void addItem(Tracker tracker) {
        String name = params[1];
        String description = params[2];
        String comment = params[3];
        Item item = new Item(name, description, comment);
        tracker.add(item);
        this.choiceOfMenu = params[4];
        this.showMenu();
    }

    /**
     * Показать все записи.
     */
    public void showAllItems(Tracker tracker) {
        Item[] itemList = tracker.findAll();
        for (Item item : itemList) {
            System.out.print(item.getName() + " ");
        }
        this.choiceOfMenu = params[1];
        this.showMenu();
    }

    /**
     * Обновить карточку.
     */
    public void updateItem(Tracker tracker) {
        String idItemToChange = params[1];
        Item currentItem = tracker.findById(idItemToChange);
        String name = params[2];
        String description = params[3];
        Item changedItem = new Item(name, description, "");
        changedItem.setId(currentItem.getId());
        tracker.update(changedItem);
        this.choiceOfMenu = params[4];
        this.showMenu();
    }

    /**
     * Удалить карточку.
     */
    public void deleteItem(Tracker tracker) {
        String idItemToDelete = params[1];
        tracker.delete(idItemToDelete);
        this.choiceOfMenu = params[2];
        this.showMenu();
    }

    /**
     * Показать по запросу id.
     */
    public void showItemById(Tracker tracker) {
        String idItemToFind = params[1];
        Item foundItem = tracker.findById(idItemToFind);
        System.out.print(foundItem.getName());
        this.choiceOfMenu = params[2];
        this.showMenu();
    }

    /**
     * Показать все по запросу имени.
     */
    public void showAllFoundByName(Tracker tracker) {
        String nameItemToFind = params[1];
        Item[] itemList = tracker.findByName(nameItemToFind);
        for (Item item : itemList) {
            System.out.print(item.getName() + " ");
        }
        this.choiceOfMenu = params[2];
        this.showMenu();
    }
}

package ru.job4j.applications;

//класс одного из действий
class UpdateItem extends BaseActions {

    UpdateItem(int key, String info) {
        super(key, info);
    }

    public int key() {
        return 3;
    }
    public void execute(Input input, Tracker tracker) {
        String idItemToChange = input.ask("please insert id for updating");
        Item currentItem = tracker.findById(idItemToChange);
        Item updatedItem = null;
        if (currentItem != null) {
            String name = input.ask("Please enter new name. Old version: " + currentItem.getName());
            String description = input.ask("Please enter new description. Old version: " + currentItem.getDescription());

            Item changedItem = new Item(name, description, "");
            changedItem.setId(currentItem.getId());
            tracker.update(changedItem);

            updatedItem = tracker.findById(changedItem.getId());
            System.out.println("Item has been changed. id: " + updatedItem.getId()
                    + " name: " + updatedItem.getName()
                    + " description: " + updatedItem.getDescription()
                    + " comment: " + updatedItem.getComments()[0]);
        } else {
            System.out.println("id is incorrect");
        }
    }
}

//Основной класс
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];
    private int iNumber = 0;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    //заполняет меню
    public void fillActions() {
        this.actions[iNumber++] = this.new AddItem(iNumber + 1, "Add new item");
        this.actions[iNumber++] = new MenuTracker.ShowAllItems(iNumber + 1, "Show all items");
        this.actions[iNumber++] = new UpdateItem(iNumber + 1, "Edit item");
        this.actions[iNumber++] = this.new DeleteItem(iNumber + 1, "Delete item");
        this.actions[iNumber++] = this.new ShowItemById(iNumber + 1, "Find item by Id");
        this.actions[iNumber++] = this.new ShowAllFoundByName(iNumber + 1, "Find items by name");
    }

    //отображает меню
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("7. Exit Program");
    }

    public void select(int key) {
        //в тестах использовалось меню, начиная с нуля. Должно вызываться this.action[key-1]
        this.actions[key].execute(this.input, this.tracker);
    }

    private class AddItem extends BaseActions {

        AddItem(int key, String info) {
            super(key, info);
        }

        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter name");
            String description = input.ask("Please enter description");
            String comment = input.ask("Please enter comment");
            Item item = new Item(name, description, comment);
            Item addedItem = tracker.add(item);
            System.out.println("Item has been added. id: " + addedItem.getId()
                    + " name: " + addedItem.getName()
                    + " description: " + addedItem.getDescription()
                    + " comment: " + addedItem.getComments()[0]);
        }
    }

    private static class ShowAllItems extends BaseActions {

        ShowAllItems(int key, String info) {
            super(key, info);
        }

        public int key() {
            return 2;
        }

        public void execute(Input input, Tracker tracker) {
            Item[] itemList = tracker.findAll();
            for (Item item : itemList) {
                System.out.println("Item name: " + item.getName()
                        + " description: " + item.getDescription()
                        + " comment: " + item.getComments()[0]);
            }
        }
    }

    private class DeleteItem extends BaseActions {

        DeleteItem(int key, String info) {
            super(key, info);
        }

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String idItemToDelete = input.ask("please insert id for deleting");
            tracker.delete(idItemToDelete);
            System.out.println("If id was correct, Item has been deleted");
        }
    }

    private class ShowItemById extends BaseActions {

        ShowItemById(int key, String info) {
            super(key, info);
        }

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String idItemToFind = input.ask("please insert id of requested item");
            Item foundItem = tracker.findById(idItemToFind);
            if (foundItem != null) {
                System.out.print("name: " + foundItem.getName()
                        + " description: " + foundItem.getDescription()
                        + " comment: " + foundItem.getComments()[0]);
            } else {
                System.out.println("Item has not been found! Please check id.");
            }
        }
    }

    private class ShowAllFoundByName extends BaseActions {

        ShowAllFoundByName(int key, String info) {
            super(key, info);
        }

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            String nameItemToFind = input.ask("please insert name for searching");
            Item[] itemList = tracker.findByName(nameItemToFind);
            //проверяем, что как минимум один элемент есть
            if (itemList.length > 0) {
                for (Item item : itemList) {
                    System.out.println("Item name: " + item.getName()
                            + " description: " + item.getDescription()
                            + " comment: " + item.getComments()[0]);
                }
            } else {
                System.out.println("No item found matched: " + nameItemToFind);
            }
        }
    }
}

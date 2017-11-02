package ru.job4j.applications;

//класс одного из действий
class UpdateItem implements UserAction {
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
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}

//Основной класс
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    //заполняет меню
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAllItems();
        this.actions[2] = new UpdateItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new ShowItemById();
        this.actions[5] = this.new ShowAllFoundByName();
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

    private class AddItem implements UserAction {
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

        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }

    private static class ShowAllItems implements UserAction {
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
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    private class DeleteItem implements UserAction {
        public int key() {
            return 4;
        }
        public void execute(Input input, Tracker tracker) {
            String idItemToDelete = input.ask("please insert id for deleting");
            tracker.delete(idItemToDelete);
            System.out.println("If id was correct, Item has been deleted");
        }
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    private class ShowItemById implements UserAction {
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
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    private class ShowAllFoundByName implements UserAction {
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
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}

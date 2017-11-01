package ru.job4j.applications;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тесты проверяющие поведение пользователя.
 */
public class StubInputTest extends OutputTest {
    /**
     * Добавление нового элемента.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "name", "desc", "comment", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("name"));
    }

    /**
     * Показать все записи.
     */
    @Test
    public void whenRequestedAllItemsThenTrackerShowAllOfThem() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Имя", "Описание", "Комментарий"));
        tracker.add(new Item("Имя1", "Описание", "Комментарий"));
        tracker.add(new Item("Имя2", "Описание", "Комментарий"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();

        String result = "Item name: Имя description: Описание comment: Комментарий\r\n"
                        + "Item name: Имя1 description: Описание comment: Комментарий\r\n"
                        + "Item name: Имя2 description: Описание comment: Комментарий\r\n";
        assertThat(result, is(output.toString()));
    }

    /**
     * Обновить карточку.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Имя", "Описание", "Комментарий"));
        Input input = new StubInput(new String[]{"2", item.getId(), "name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("name"));
    }

    /**
     * Удалить карточку.
     */
    @Test
    public void whenDeleteItemThenItsIdDoesNotExistInTheItemList() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Имя", "Описание", "Комментарий"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();

        boolean result = false;
        for (Item i : tracker.findAll()) {
            if (i.getId().equals(item.getId())) {
                result = true;
            }
        }
        boolean expected = false;
        assertThat(result, is(expected));
    }

    /**
     * Показать по запросу id.
     */
    @Test
    public void whenRequestItemByIdThenTrackerShowIt() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Имя", "Описание", "Комментарий"));
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        //assertThat("name: Имя description: Описание comment: Комментарий\n", is(output.toString()));
        assertThat("name: Имя description: Описание comment: Комментарий", is(output.toString()));
    }

    /**
     * Показать все по запросу имени.
     */
    @Test
    public void whenRequestAllItemsThenTrackerShowAllWithSameNames() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Имя", "Описание", "Комментарий"));
        tracker.add(new Item("Name", "Описание", "Комментарий"));
        tracker.add(new Item("Имя2", "Описание", "Комментарий"));
        Input input = new StubInput(new String[]{"5", "Имя", "6"});
        new StartUI(input, tracker).init();

        String result = "Item name: Имя description: Описание comment: Комментарий\r\nItem name: Имя2 description: Описание comment: Комментарий\r\n";
        assertThat(result, is(output.toString()));
    }
}
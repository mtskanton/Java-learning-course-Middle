package ru.job4j.applications;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Tracker.
 * @author Anton Matsik
 * @since 29.10.2017
 */
public class TrackerTest {
    /**
     * Тест добавления по подсчету заполненых элементов в списке.
     */
    @Test
    public void whenAddNewItemTrackerHasIt() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        tracker.add(item);
        Item result = tracker.findAll()[0];
        assertThat(result, is(item));
    }

    /**
     * Тест обновления элемента заменой имени.
     */
    @Test
    public void whenUpdateItemThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        tracker.add(item);
        Item changedItem = new Item("Измененное имя", "Описание", "Комментарий");
        changedItem.setId(item.getId());
        tracker.update(changedItem);
        Item result = tracker.findAll()[0];
        assertThat(result.getName(), is(changedItem.getName()));
    }

    /**
     * Тест удаления элемента.
     */
    @Test
    public void whenDeleteItemThenNoIdInTheList() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        tracker.add(item);
        tracker.delete(item.getId());
        boolean result = false;
        for (Item i : tracker.findAll()) {
            if (i.getId() == item.getId()) {
                result = true;
            }
        }
        boolean expected = false;
        assertThat(result, is(expected));
    }

    /**
     * Тест поиска по id.
     */
    @Test
    public void whenRequestByIdThenGetName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        tracker.add(item);
        Item foundItem = tracker.findById(item.getId());
        String result = foundItem.getName();
        String expected = "Имя";
        assertThat(result, is(expected));
    }

    /**
     * Тест поиска по совпадению имени.
     */
    @Test
    public void whenRequestByNameThenListOfMatches() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        Item item1 = new Item("Name", "Описание", "Комментарий");
        Item item2 = new Item("Имя2", "Описание", "Комментарий");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Item[] result = tracker.findByName("Имя");
        assertThat(result[1].getName(), is("Имя2"));
    }

    /**
     * Тест вывода всех элементов списка.
     */
    @Test
    public void whenRequestAllThenGetAllNames() {
        Tracker tracker = new Tracker();
        Item item = new Item("Имя", "Описание", "Комментарий");
        Item item1 = new Item("Имя1", "Описание", "Комментарий");
        Item item2 = new Item("Имя2", "Описание", "Комментарий");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Item[] result = tracker.findAll();
        assertThat(result[0].getName(), is("Имя"));
        assertThat(result[1].getName(), is("Имя1"));
        assertThat(result[2].getName(), is("Имя2"));
    }
}

package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.*;

/**
 * Класс для работы со списком пользователей.
 */
@ThreadSafe
public class UserStorage {
    /**
     * Список пользователей
     */
    @GuardedBy("this")
    List<User> users = new ArrayList<>();

    /**
     * Добавление пользователя в список.
     * @param user пользователь
     * @return true если успешно
     */
    public synchronized boolean add(User user) {
        return users.add(user);
    }

    /**
     * Обновление пользователя.
     * @param user обновленный пользователь
     * @return true если успешно
     */
    public synchronized boolean update(User user) {
        boolean result = false;
        int index = users.indexOf(user.getId());
        if (index >= 0) {
            users.set(index, user);
            result = true;
        }
        return result;
    }

    /**
     * Удаление пользователя.
     * @param user пользователь
     * @return true если успешно
     */
    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    /**
     * Операция трансфера средств между пользователями User.
     * @param fromId ID пользователя для снятия средств
     * @param toId ID пользователя для зачисления средств
     * @param sum сумма трансфера
     */
    public synchronized void transfer(int fromId, int toId, int sum) {
        User from = null;
        User to = null;

        for (User user : users) {
            if (user.getId() == fromId) {
                from = user;
            }
            if (user.getId() == toId) {
                to = user;
            }
        }

        if (from != null && to != null && sum >= 0) {
            if (from.withdraw(sum)) {
                to.refill(sum);
            }
        }
    }
}

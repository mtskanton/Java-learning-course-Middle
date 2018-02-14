package storage.logic;

import storage.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация DAO интерфейса.
 * Хранение в памяти
 * Сущность - пользователь
 */
public class MemoryStorage implements StorageDao<User> {
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User getById(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void create(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        users.replace(user.getId(), user);
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }
}

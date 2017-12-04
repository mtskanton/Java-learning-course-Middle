package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Класс кеша для хранение моделей с возможность проверять валидность данных при обновлении
 */
public class NonBlockingCash {

    /**
     * Хранение моделей в ConcurrentHashMap
     */
    ConcurrentHashMap<Integer, Model> map = new ConcurrentHashMap<>();

    /**
     * Метод добавления модели
     * @param model новая модель
     */
    public void add(Model model) {
        this.map.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод удаления модели
     * @param model модель для удаления
     */
    public void delete(Model model) {
        this.map.remove(model.getId());
    }

    /**
     * Обновление модели
     * @param newModel новая модель
     * @throws OptimisticException в случае, если модель уже была изменена и теперь новая версия
     */
    public void update(Model newModel) throws OptimisticException {
        map.computeIfPresent(newModel.getId(), new BiFunction<Integer, Model, Model>() {
            @Override
            public Model apply(Integer integer, Model model) {
                if (model.getVersion() == newModel.getVersion()) {
                    model.setName(newModel.getName());
                    return model;
                }
                throw new OptimisticException("Модель уже была изменена");
            }
        });
    }
}

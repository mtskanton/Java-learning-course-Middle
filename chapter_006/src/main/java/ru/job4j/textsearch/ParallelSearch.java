package ru.job4j.textsearch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс обхода файловой системы и поиск заданного текста в файловой системе.
 * Поиск текста в файле запускается в отдельном потоке.
 */
public class ParallelSearch {

    //путь до папки откуда надо осуществлять поиск
    private String root;

    //заданный текст для поиска
    private String text;

    //расширения файлов, в которых следует делать поиск текста
    private List<String> ext;

    //хранилище результата. Резуьтат поиска - адреса файлов, в которых содержится искомый текст
    private List<String> result = new ArrayList<>();

    //для запуска потоков используется пул потоков.
    //количество потоков определяется количеством процессоров.
    private ExecutorService executor;

    //Лок добавления результата в общий список
    private Lock lock = new ReentrantLock();

    ParallelSearch(String root, String text, List<String> ext) {
        this.root = root;
        this.text = text;
        this.ext = ext;
        int cores = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(cores);
    }

    /**
     * Метод запуска поиска.
     */
    public synchronized void search() throws InterruptedException, ExecutionException {
        File dir = new File(this.root);
        directorySearch(dir);

        executor.shutdown();
        //Ожидание завершения всех задач в пуле потоков
        while (!executor.isTerminated()) {
            continue;
        }
    }

    /**
     * Метод рекурсивного прохода по всем каталогам.
     * @param directory директория поиска
     */
    private void directorySearch(File directory) throws InterruptedException, ExecutionException {
        if (directory.list() != null) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    //если разрешение файла есть среди искомых
                    if (ext.contains(getFileExtension(file))) {
                        //запускает поток поиска текста непосредственно в файле
                        executor.execute(new TextSearch(this, this.text, file.getPath()));
                    }
                } else {
                    directorySearch(file);
                }
            }
        }
    }

    /**
     * Метод определения расширения файла
     */
    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        //если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            //вырезаем все знаки после последней точки в названии файла
             extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return extension;
    }

    /**
     * Метод добавляет результат поиска в список результатов.
     * @param found адрес файла с искомым текстом
     */
    public void addResult(String found) {
        synchronized (lock) {
            this.result.add(found);
        }
    }

    /**
     * Метод получения результата поиска
     * @return список адресов файлов, содержащих искомый текст
     */
    public List<String> getResult() {
        return  result;
    }

    public static void main(String[] args) {
        //указываем расширения файлов
        List<String> ext = new ArrayList<>(Arrays.asList("xml", "txt"));
        ParallelSearch ps = new ParallelSearch("D:\\", "text", ext);
        try {
            ps.search();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //отображаем результат
        List<String> result = ps.getResult();
        System.out.println(String.format("Запрошенный текст содержат %s файл (а, ов):", result.size()));
        for (String r : result) {
            System.out.println(r);
        }
    }
}
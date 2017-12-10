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
    List<String> result = new ArrayList<>();

    //для запуска потоков используется пул потоков.
    //количество потоков определяется количеством процессоров.
    ExecutorService executor;

    //счетчик количества запущенных потоков для выбора времени остановки пула потоков
    //инкрементация в момент запуска потока
    //декрементация в потоке в момент окончания работы с файлом
    private int counter = 0;

    //лок счетчика
    private Lock counterLock = new ReentrantLock();

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

        //перевод в ожидание каждую секунду до момента завершения всех задач переданных в пул пооков.
        //по завершению поиска во всех файлах приостановка пула потоков
        while (counter > 0) {
           System.out.println("wait");
           wait(1000);
        }
        executor.shutdownNow();
    }

    /**
     * Метод рекурсивного прохода по всем каталогам.
     * @param directory директория поиска
     */
    public void directorySearch(File directory) throws InterruptedException, ExecutionException {
        if (directory.list() != null) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    //если разрешение файла есть среди искомых
                    if (ext.contains(getFileExtension(file))) {
                        //прибавляем счетчик при добавлении каждого файла на проверку
                        synchronized (counterLock) {
                            this.counter++;
                        }
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
     * Метод вычитает счетчик, когда какой-либо из потоков прекратил поиск в файле.
     */
    public void decrementCounter() {
        synchronized (counterLock) {
            this.counter--;
        }
    }

    /**
     * Метод добавляет результат поиска в список результатов.
     * @param found адрес файла с искомым текстом
     */
    public void addResult(String found) {
        synchronized (counterLock) {
            this.result.add(found);
            this.decrementCounter();
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
package ru.job4j.file;

import java.io.File;

/**
 * Практика использования класса File пакета java.io
 */
public class FileExample {

    /**
     * Метод выводит список файлов определенного расширения.
     * @param extensions список расширений. Записываются через запятую
     */
    public void chooseFilesByExtension(String extensions) {

        //выбираем директорию, которую будем проверять на наличие файлов нужного расширения
        File directory = new File("D:\\testDir");

        //создаем фильтр по выбранным расширениям файлов
        FileFilterExample filter = new FileFilterExample(extensions);

        //собираем список файлов, удовлетворяющих условию
        File[] files = directory.listFiles(filter);

        //выводим имена файлов
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    public static void main(String[] args) {
        FileExample fe = new FileExample();

        String ext = "docx, txt";
        fe.chooseFilesByExtension(ext);
    }

}

package ru.job4j.file;

import java.io.File;
import java.io.FileFilter;

/**
 * Пример фильтра файлов по соответствию списку расширений
 */
public class FileFilterExample implements FileFilter {

    private String[] extensions;

    FileFilterExample(String ext) {
        extensions = ext.split(",");
    }

    public boolean accept(File file) {
        boolean result = false;

        if (file.isFile()) {
            String filename = file.getName();
            String extension = "";

            int point = filename.lastIndexOf(".");
            //если в имени файла есть точка (!=-1) и она не первая (!=0)
            if (point > 0) {
                extension = filename.substring(point + 1);
            }

            //сверяем с массивом всех расширений
            for (String e : extensions) {
                if (extension.equals(e.trim())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}

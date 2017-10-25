package ru.job4j;

/**
 * Проверка на вхождение субстроки в строку.
 * @author Anton Matsik
 * @since 23.10.2017
 */
public class Subword {
    /**
     * Проверяет вхождение субстроки в строку.
     * @param origin исходная строка
     * @param sub строка для проверки вхождения
     * @return true если полное совпадение
     */
    public boolean contains(String origin, String sub) {
        //перевод строк в массив символов
        char[] originToChar = origin.toCharArray();
        char[] subToChar = sub.toCharArray();

        boolean result = false;
        for (int i = 0; i < originToChar.length; i++) {
            //совпадение первой буквы
            if (originToChar[i] == subToChar [0]) {
                //если субстрока один символ, то совпадение полное
                if (subToChar.length == 1) {
                    result = true;
                } else {
                    //сравнение двух массивов посимвольно
                    // начиная со второго для субстроки и следующего для оригинальной строки
                    i++;
                    for (int j = 1; j < subToChar.length && i < originToChar.length; j++, i++) {
                        if (originToChar[i] == subToChar[j]) {
                            //если досчитали до конца массива субстроки, то совпадение полное
                            if (subToChar.length == j + 1) {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}

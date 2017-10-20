package ru.job4j;

/**
* Calculate. Вывод надписи Hello World
*
* @author Anton Matsik (mailto:mtsk_anton@yandex.ru)
* @since 20.10.2017
*/
public class Calculate {
	
	/**
	* Метод запуска программы
	* Вывод строки в консоль
	* @param args
	*/
	public static void main(String[] args){
		System.out.println("Hello World");
	}

	/**
	* Method echo.
	* @param name Your name.
	* @return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}	
}
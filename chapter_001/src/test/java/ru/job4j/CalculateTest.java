package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* CalculateTest
*
* @author Anton Matsik (mailto:mtsk_anton@yandex.ru)
* @since 20.10.2017
*/
public class CalculateTest {
	/**
	* Test echo.
	*/ 	
	@Test
	public void whenTakeNameThenTreeEchoPlusName() {
		String input = "Anton Matsik";
		String expect = "Echo, echo, echo : Anton Matsik"; 
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
 
}
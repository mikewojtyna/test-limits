/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.bad.Calculator;

/**
 * @author goobar
 *
 */
class CalculatorTooMuchTest
{

	@DisplayName("should add two positive numbers")
	@Test
	void testAdd0() throws Exception
	{
		assertThat(Calculator.add(1, 2)).isEqualTo(3);
	}

	@DisplayName("should add zeroes")
	@Test
	void testAdd1() throws Exception
	{
		assertThat(Calculator.add(0, 0)).isEqualTo(0);
	}

	@DisplayName("should add zero and positive number")
	@Test
	void testAdd2() throws Exception
	{
		assertThat(Calculator.add(0, 2)).isEqualTo(2);
	}

	@DisplayName("should add two negative numbers")
	@Test
	void testAdd3() throws Exception
	{
		assertThat(Calculator.add(-1, -2)).isEqualTo(-3);
	}

	@DisplayName("should add a lot of unnecessary numbers")
	@Test
	void testAddMany() throws Exception
	{
		assertThat(Calculator.add(1, 2)).isEqualTo(3);
		assertThat(Calculator.add(1, 3)).isEqualTo(4);
		assertThat(Calculator.add(1, 4)).isEqualTo(5);
		assertThat(Calculator.add(1, 5)).isEqualTo(6);
		// ... and so on
		assertThat(Calculator.add(2, 1)).isEqualTo(3);
		assertThat(Calculator.add(3, 1)).isEqualTo(4);
		assertThat(Calculator.add(4, 1)).isEqualTo(5);
		assertThat(Calculator.add(5, 1)).isEqualTo(6);
		// ... and so on
		assertThat(Calculator.add(2, 2)).isEqualTo(4);
		assertThat(Calculator.add(2, 3)).isEqualTo(5);
		assertThat(Calculator.add(2, 4)).isEqualTo(6);
		assertThat(Calculator.add(2, 5)).isEqualTo(7);
	}

}

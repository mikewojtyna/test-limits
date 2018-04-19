/**
 *
 */
package pro.buildmysoftware.testlimits;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author goobar
 *
 */
class CalculatorPerfectTest
{
	@DisplayName("should add two integer numbers")
	@ParameterizedTest(name = "{0} + {1} should equal {2}")
	@CsvSource({
		//
		"0, 0, 0",
		//
		"0, 1, 1",
		//
		"1, 0, 1",
		//
		"1, 2, 3",
		//
		"2, 1, 3",
		//
		"2, 4, 6",
		//
		"4, 2, 6",
		//
		"-1, 0, -1",
		//
		"0, -1, -1",
		//
		"-1, -1, -2",
		//
		"-1, -2, -3",
		//
		"-2, -1, -3",
		//
		"-2, -4, -6",
		//
		"-4, -2, -6",
		//
		"-1, 1, 0",
		//
		"-2, 2, 0",
		//
		"-3, 3, 0",
		//
		"-1, 2, 1",
		//
		"-1, 3, 2",
		//
		"-2, 3, 1",
		//
		"-2, 4, 2",
		//
		"-1234, 1234, 0",
		// uncomment this one :)
		// "1234, -1234, 0"
	})
	void testAdd(int a, int b, int expected) throws Exception
	{
		assertThat(Calculator.add(a, b)).isEqualTo(expected);
	}
}

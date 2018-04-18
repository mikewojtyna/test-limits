/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.good.converter.converters.CarToBikeConverter;

/**
 * @author goobar
 *
 */
class CarToBikeConverterTest
{

	private Converter<Car, Bike> converter;

	@DisplayName("should convert Car to Bike")
	@Test
	void convert() throws Exception
	{
		// given
		Car car = new Car();

		// when
		Optional<Bike> bike = converter.convert(car);

		// then
		assertThat(bike).isPresent();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		converter = new CarToBikeConverter();
	}

}

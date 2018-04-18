/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.good.converter.converters.ObjectToStringConverter;

/**
 * @author goobar
 *
 */
class ObjectToStringConverterTest
{

	private Converter<Object, String> converter;

	@DisplayName("should convert car and bike to string")
	@Test
	void convert() throws Exception
	{
		// given
		Car car = new Car();
		Bike bike = new Bike();

		// when
		Optional<String> carString = converter.convert(car);
		Optional<String> bikeString = converter.convert(bike);

		// then
		assertThat(carString).hasValue("Car");
		assertThat(bikeString).hasValue("Bike");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		converter = new ObjectToStringConverter();
	}

}

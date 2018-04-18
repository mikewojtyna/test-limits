/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.good.converter.converters.BikeToCarConverter;

/**
 * @author goobar
 *
 */
class BikeToCarConverterTest
{

	private Converter<Bike, Car> converter;

	@DisplayName("should convert Bike to Car")
	@Test
	void convert() throws Exception
	{
		// given
		Bike bike = new Bike();

		// when
		Optional<Car> car = converter.convert(bike);

		// then
		assertThat(car).isPresent();
		// TODO: test detailed conversion rules for this convter
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		converter = new BikeToCarConverter();
	}

}

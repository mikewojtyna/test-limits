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
import pro.buildmysoftware.testlimits.good.converter.converters.CarToBikeConverter;
import pro.buildmysoftware.testlimits.good.converter.converters.ObjectToStringConverter;

/**
 * @author goobar
 *
 */
class ConverterTest
{

	private ConversionContext conversionContext;

	@DisplayName("should convert Bike and Car using conversion context")
	@Test
	void convertAll() throws Exception
	{
		// given
		Bike bike = new Bike();
		Car car = new Car();
		conversionContext.registerConverter(new BikeToCarConverter(),
			Car.class);
		conversionContext.registerConverter(new CarToBikeConverter(),
			Bike.class);
		conversionContext.registerConverter(
			new ObjectToStringConverter(), String.class);

		// when
		Optional<Car> carFromBike = conversionContext.convert(bike,
			Car.class);
		Optional<Bike> bikeFromCar = conversionContext.convert(car,
			Bike.class);
		Optional<String> bikeString = conversionContext.convert(bike,
			String.class);
		Optional<String> carString = conversionContext.convert(car,
			String.class);

		// then
		assertThat(carFromBike).isPresent();
		assertThat(bikeFromCar).isPresent();
		assertThat(bikeString).hasValue("Bike");
		assertThat(carString).hasValue("Car");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		conversionContext = new ConversionContext();
	}

}

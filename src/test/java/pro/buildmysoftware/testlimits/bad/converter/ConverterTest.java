/**
 *
 */
package pro.buildmysoftware.testlimits.bad.converter;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author goobar
 *
 */
class ConverterTest
{

	@Parameters
	public static Stream<Arguments> params()
	{
		return Stream.of(

			// convert bike -> car
			Arguments.of(bike(), Car.class),
			// convert bike -> motorcycle
			Arguments.of(bike(), Motorcycle.class),
			// convert car -> bike
			Arguments.of(car(), Bike.class),
			// convert car -> motorcycle
			Arguments.of(car(), Motorcycle.class),
			// convert motorcycle -> car
			Arguments.of(motorcycle(), Car.class),
			// convert motorcycle -> bike
			Arguments.of(motorcycle(), Bike.class)

		);
	}

	/**
	 * @return
	 */
	private static Object bike()
	{
		return new Bike();
	}

	/**
	 * @return
	 */
	private static Object car()
	{
		return new Car();
	}

	/**
	 * @return
	 */
	private static Object motorcycle()
	{
		return new Motorcycle();
	}

	private Converter converter;

	@DisplayName("should convert supported objects")
	@ParameterizedTest(name = "source = {0}, target class = {1}")
	@MethodSource("params")
	void convert(Object source, Class<?> target) throws Exception
	{
		// when
		Optional<?> result = converter.convert(source, target);

		// then
		assertThat(result.get()).isInstanceOf(target);
	}

	@DisplayName("should convert all available types to string")
	@Test
	void convertToString() throws Exception
	{
		assertThat(converter.convert(bike(), String.class))
			.hasValue(("Bike"));
		assertThat(converter.convert(car(), String.class))
			.hasValue("Car");
		assertThat(converter.convert(motorcycle(), String.class))
			.hasValue("Motorcycle");
	}

	@DisplayName("should convert to empty Optional if conversion is not supported")
	@Test
	void emptyIfNotSupported() throws Exception
	{
		// when
		Optional<Banana> result = converter.convert(bike(),
			Banana.class);

		// then
		assertThat(result).isEmpty();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		converter = new Converter();
	}

	/**
	 * @author goobar
	 *
	 */
	public class Banana
	{

	}

}

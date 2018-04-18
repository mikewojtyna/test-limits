/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter.converters;

import java.util.Optional;
import pro.buildmysoftware.testlimits.good.converter.Bike;
import pro.buildmysoftware.testlimits.good.converter.Car;
import pro.buildmysoftware.testlimits.good.converter.Converter;

/**
 * @author goobar
 *
 */
public class CarToBikeConverter implements Converter<Car, Bike>
{

	/* (non-Javadoc)
	 * @see pro.buildmysoftware.testlimits.good.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Optional<Bike> convert(Car source)
	{
		return Optional.of(new Bike());
	}

}

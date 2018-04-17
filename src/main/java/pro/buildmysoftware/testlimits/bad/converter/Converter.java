/**
 *
 */
package pro.buildmysoftware.testlimits.bad.converter;

import java.util.Optional;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class Converter
{
	public <T, S> Optional<T> convert(S source, Class<T> target)
	{
		if (target == String.class)
		{
			return cast(target, source.toString());
		}
		if (target == source.getClass())
		{
			return cast(target, source);
		}
		if (target == Car.class && source instanceof Bike)
		{
			return cast(target,
				upgradeBikeToCar(castToBike(source)));
		}
		if (target == Bike.class && source instanceof Car)
		{
			return cast(target,
				downgradeCarToBike(castToCar(source)));
		}
		if (target == Motorcycle.class && source instanceof Bike)
		{
			return cast(target,
				upgradeBikeToMotorcycle(castToBike(source)));
		}
		if (target == Motorcycle.class && source instanceof Car)
		{
			return cast(target,
				changeCarToMotorcycle(castToCar(source)));
		}
		if (target == Car.class && source instanceof Motorcycle)
		{
			return cast(target, changeMotorcycleToCar(
				castToMotorcycle(source)));
		}
		if (target == Bike.class && source instanceof Motorcycle)
		{
			return cast(target, downgradeMotorcycleToBike(
				castToMotorcycle(source)));
		}
		return Optional.empty();
	}

	/**
	 * @param <T>
	 * @param object
	 * @return
	 */
	private <T> Optional<T> cast(Class<T> target, Object object)
	{
		return Optional.of(target.cast(object));
	}

	/**
	 * @param source
	 * @return
	 */
	private Bike castToBike(Object source)
	{
		return Bike.class.cast(source);
	}

	/**
	 * @param source
	 * @return
	 */
	private Car castToCar(Object source)
	{
		return Car.class.cast(source);
	}

	/**
	 * @param source
	 * @return
	 */
	private Motorcycle castToMotorcycle(Object source)
	{
		return Motorcycle.class.cast(source);
	}

	/**
	 * @param castToCar
	 * @return
	 */
	private Motorcycle changeCarToMotorcycle(Car castToCar)
	{
		return new Motorcycle();
	}

	/**
	 * @param castToMotorcycle
	 * @return
	 */
	private Object changeMotorcycleToCar(Motorcycle castToMotorcycle)
	{
		return new Car();
	}

	/**
	 * @param car
	 * @return
	 */
	private Bike downgradeCarToBike(Car car)
	{
		return new Bike();
	}

	/**
	 * @param castToMotorcycle
	 * @return
	 */
	private Object downgradeMotorcycleToBike(Motorcycle castToMotorcycle)
	{
		return new Bike();
	}

	/**
	 * @param bike
	 * @return
	 */
	private Car upgradeBikeToCar(Bike bike)
	{
		return new Car();
	}

	/**
	 * @param bike
	 * @return
	 */
	private Motorcycle upgradeBikeToMotorcycle(Bike bike)
	{
		return new Motorcycle();
	}

}

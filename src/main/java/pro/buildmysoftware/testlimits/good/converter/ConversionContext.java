/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This conversion context <strong>is not thread-safe</strong>.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ConversionContext
{
	private final Map<Class<?>, Collection<Converter<Object, Object>>> converterMap;

	/**
	 *
	 */
	public ConversionContext()
	{
		converterMap = new HashMap<>();
	}

	/**
	 * Converts the given source object. Conversion is delegated to
	 * registered converterMap.
	 *
	 * @param source
	 * @param target
	 * @return the converted object or empty optional if there's no
	 *         registered converter that is able to perform the conversion
	 */
	public <T> Optional<T> convert(Object source, Class<T> target)
	{
		return useRightConverter(source, target);
	}

	/**
	 * This method <strong>is not thread-safe</strong>.
	 *
	 * @param <S>
	 *
	 * @param converter
	 *                a converter to add
	 * @param target
	 *                target type of the converter
	 */
	public <T> void registerConverter(Converter<?, ?> converter,
		Class<T> target)
	{
		Collection<Converter<Object, Object>> rightConverters = rightConverters(
			target);
		if (rightConverters == null)
		{
			initConvertersFor(target, converter);
		}
		else
		{
			addConverter(converter, rightConverters);
		}
	}

	/**
	 * @param converter
	 * @param rightConverters
	 */
	@SuppressWarnings("unchecked")
	private void addConverter(Converter<?, ?> converter,
		Collection<Converter<Object, Object>> rightConverters)
	{
		rightConverters.add((Converter<Object, Object>) converter);
	}

	/**
	 * @param <T>
	 * @param target
	 * @param converter
	 */
	@SuppressWarnings("unchecked")
	private <T> void initConvertersFor(Class<T> target,
		Converter<?, ?> converter)
	{
		Collection<Converter<Object, Object>> converters = new ArrayList<>();
		converters.add((Converter<Object, Object>) converter);
		converterMap.put(target, converters);
	}

	/**
	 * @param target
	 * @return
	 */
	private Collection<Converter<Object, Object>> rightConverters(
		Object target)
	{
		return converterMap.get(target);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> Optional<T> useRightConverter(Object source,
		Class<T> target)
	{
		Collection<Converter<Object, Object>> rightConverters = rightConverters(
			target);
		if (rightConverters == null)
		{
			return Optional.empty();
		}

		return (Optional<T>) rightConverters.stream()
			.map(c -> c.convert(source)).filter(Optional::isPresent)
			.findFirst().get();
	}
}

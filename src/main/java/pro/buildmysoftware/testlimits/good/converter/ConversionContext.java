/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import java.util.ArrayList;
import java.util.Collection;
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
	private Map<Class<?>, Collection<Converter<Object, ?>>> converters;

	/**
	 * Converts the given source object. Conversion is delegated to
	 * registered converters.
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
	 * @param converter
	 *                a converter to add
	 * @param target
	 *                target type of the converter
	 */
	public <T> void registerConverter(Converter<Object, T> converter,
		Class<T> target)
	{
		Collection<Converter<Object, ?>> rightConverters = converters
			.get(target);
		if (rightConverters == null)
		{
			converters.put(target, new ArrayList<>());
		}
		else
		{
			rightConverters.add(converter);
		}
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> Optional<T> useRightConverter(Object source,
		Class<T> target)
	{
		return (Optional<T>) converters.get(target).stream()
			.map(c -> c.convert(source)).filter(Optional::isPresent)
			.findFirst().get();
	}
}

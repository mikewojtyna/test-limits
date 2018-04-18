/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter.converters;

import java.util.Optional;
import pro.buildmysoftware.testlimits.good.converter.Converter;

/**
 * @author goobar
 *
 */
public class ObjectToStringConverter implements Converter<Object, String>
{

	/* (non-Javadoc)
	 * @see pro.buildmysoftware.testlimits.good.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Optional<String> convert(Object source)
	{
		return Optional.of(source.toString());
	}

}

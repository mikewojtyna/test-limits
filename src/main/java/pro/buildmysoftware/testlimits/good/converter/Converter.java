/**
 *
 */
package pro.buildmysoftware.testlimits.good.converter;

import java.util.Optional;

/**
 * A generic converter interface.
 *
 * @author goobar
 * @param <S>
 *                source type
 * @param <T>
 *                target type
 *
 */
@SuppressWarnings("javadoc")
public interface Converter<S, T>
{
	/**
	 * Converts the given soruce object.
	 *
	 * @param source
	 * @return
	 */
	Optional<T> convert(S source);
}

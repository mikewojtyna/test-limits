/**
 *
 */
package pro.buildmysoftware.testlimits.bad.spring;

import java.util.Arrays;
import org.springframework.stereotype.Service;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
@Service
public class ResidentsService
{
	public Iterable<String> loadFromCastleBlack()
	{
		return Arrays.asList("Jeorh", "John", "Samwell");
	}

	public Iterable<String> loadFromKingsLanding()
	{
		return Arrays.asList("Gregor", "Qyburn", "Jamie");
	}
}

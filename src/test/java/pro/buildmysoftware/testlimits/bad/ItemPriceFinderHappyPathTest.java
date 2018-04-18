/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author goobar
 *
 */
class ItemPriceFinderHappyPathTest
{

	/*@DisplayName("should return zero as big decimal when item doesn't exist")
	@Test
	void testEmpty() throws Exception
	{
		// given
		ItemPriceFinder finder = new ItemPriceFinder();
	
		// when
		BigDecimal price = finder.findPrice("Monkey");
	
		// then
		assertThat(price).isEqualTo(BigDecimal.valueOf(0));
	}*/

	@DisplayName("should find price of rocket launcher")
	@Test
	void testFind() throws Exception
	{
		// given
		ItemPriceFinder finder = new ItemPriceFinder();

		// when
		BigDecimal price = finder.findPrice("Rocket launcher");

		// then
		assertThat(price).isEqualTo(BigDecimal.valueOf(1_000_000));
		// TODO: Did we forget about something? Uncomment the other
		// test.
	}

}

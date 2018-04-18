/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author goobar
 *
 */
class XmasCounterTest
{

	@DisplayName("should return number of days until xmas")
	@Test
	void daysUntilXmas() throws Exception
	{
		// given
		XmasCounter counter = new XmasCounter();

		// when
		@SuppressWarnings("unused")
		long daysUntilXmas = counter.daysUntilXmas();

		// then
		// So, now what? How can we assert the number of days? We don't
		// know what's the current date. And if we depend on system
		// Clock this test is not repeatable. Oops!
		fail("Don't know how to test that!");
	}

}

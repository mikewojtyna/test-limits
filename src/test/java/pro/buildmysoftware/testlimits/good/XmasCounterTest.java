/**
 *
 */
package pro.buildmysoftware.testlimits.good;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author goobar
 *
 */
class XmasCounterTest
{

	@DisplayName("should return number of days until xmas when it's 5 days before 25th of December using Clock")
	@Test
	void daysUntilXmasClock() throws Exception
	{
		// given
		XmasCounter counter = new XmasCounter();
		// fixed clock that always returns date 2010-12-20 (yyyy-MM-dd)
		Clock clock = Clock.fixed(LocalDate.of(2010, 12, 20)
			.atStartOfDay().toInstant(ZoneOffset.UTC),
			ZoneOffset.UTC);

		// when
		long daysUntilXmas = counter.daysUntilXmas(clock);

		// then
		// Now, it's very easy to test this! Clock is configured to
		// return date 2010-12-20, xmas is on 25th of December, so the
		// result should be 5.
		assertThat(daysUntilXmas).isEqualTo(5);
	}

	@DisplayName("should return number of days until xmas when it's 5 days before 25th of December using current date")
	@Test
	void daysUntilXmasDate() throws Exception
	{
		// given
		XmasCounter counter = new XmasCounter();
		LocalDate currentDate = LocalDate.of(2010, 12, 20);

		// when
		long daysUntilXmas = counter.daysUntilXmas(currentDate);

		// then
		// This version is even simpler. We simply passed the current
		// date, so we don't need to bother about Clock configuration.
		assertThat(daysUntilXmas).isEqualTo(5);
	}

}

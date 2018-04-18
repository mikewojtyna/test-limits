/**
 *
 */
package pro.buildmysoftware.testlimits.good;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author goobar
 *
 */
public class XmasCounter
{
	/**
	 * Counts the days until xmas.
	 *
	 * @param clock
	 *                a clock used to obtain the current date
	 *
	 * @return number of days until xmas
	 */
	public long daysUntilXmas(Clock clock)
	{
		return daysUntilXmas(LocalDate.now(clock));
	}

	/**
	 * Counts the days until xmas.
	 *
	 * @param now
	 *                the current date
	 *
	 * @return number of days until xmas
	 */
	public long daysUntilXmas(LocalDate now)
	{
		LocalDate xmasThisYear = LocalDate.of(now.getYear(), 12, 25);
		if (now.isAfter(xmasThisYear))
		{
			return ChronoUnit.DAYS.between(now,
				xmasThisYear.plusYears(1));
		}
		else
		{
			return ChronoUnit.DAYS.between(now, xmasThisYear);
		}
	}
}

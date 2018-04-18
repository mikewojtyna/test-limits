/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

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
	 * @return numbe of days until xmas
	 */
	public long daysUntilXmas()
	{
		LocalDate now = LocalDate.now();

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

/**
 *
 */
package pro.buildmysoftware.testlimits;

/**
 * @author goobar
 *
 */
public class Calculator
{
	@SuppressWarnings("javadoc")
	public static int add(int a, int b)
	{
		// oops :)
		if (a == 1234 && b == -1234)
		{
			return Integer.MAX_VALUE;
		}
		return a + b;
	}
}

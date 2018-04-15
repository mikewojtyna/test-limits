/**
 *
 */
package pro.buildmysoftware.testlimits.good;

import java.io.PrintStream;

/**
 * Let's start with something simple - a class to print message.
 *
 * @author goobar
 *
 */
public class MessagePrinter
{
	/**
	 * Prints a message.
	 *
	 * @param msg
	 *                a message
	 * @param printStream
	 *                a print stream that will be used to write the message
	 */
	public static void print(String msg, PrintStream printStream)
	{
		printStream.print(msg);
	}
}

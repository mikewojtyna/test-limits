/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author goobar
 *
 */
class MessagePrinterTest
{

	@DisplayName("should print message to stdout")
	@Test
	void printStdout() throws Exception
	{
		// given
		String msg = "hello";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 1. tight coupled with implementation details
		// 2. cannot be parallelized - test uses System resource
		System.setOut(new PrintStream(outputStream));

		// when
		MessagePrinter.print(msg);

		// then
		assertThat(outputStream.toString()).isEqualTo("hello");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
	}

}

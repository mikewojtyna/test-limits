/**
 *
 */
package pro.buildmysoftware.testlimits.good;

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

	@DisplayName("should print message to output stream")
	@Test
	void printMsg() throws Exception
	{
		// given
		String msg = "hello";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// when
		MessagePrinter.print(msg, new PrintStream(outputStream));

		// then
		assertThat(outputStream.toString()).isEqualTo(msg);
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

/**
 *
 */
package pro.buildmysoftware.testlimits.bad.excelnate;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Now, I won't even try to start writing tests for that... That would be
 * totally insane. We would need to test reading files, row mapping and cell
 * mapping at the same time. Everything coupled together. Also, imagine how to
 * test exception handling! And what's worst - we would be forced to test cell
 * mappers and row mapper through real physical files. Just pure madness.
 *
 * @author goobar
 *
 */
class ExcelReaderTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void should_DoAllTheOtherStuff() throws Exception
	{
		fail("Not yet implemented");
	}

	@Test
	void should_HandleCellConversionExceptions() throws Exception
	{
		// TODO: the only way to test that would be to place REAL DATA
		// in physical Excel file... I don't even try to do that here
		fail("Not yet implemented");
	}

	@Test
	void should_MapCell() throws Exception
	{
		// TODO: create a new test for every cell mapper ever added
		fail("Not yet implemented");
	}

	@Test
	void should_MapComplexObject() throws Exception
	{
		// TODO: the only way to test that would be to place REAL DATA
		// in physical Excel file... I don't even try to do that here
		fail("Not yet implemented");
	}

	@Test
	void should_MapRow() throws Exception
	{
		// TODO: the only way to test that would be to place REAL DATA
		// in physical Excel file... I don't even try to do that here
		fail("Not yet implemented");
	}

	@Test
	void should_ReadExcelFile() throws Exception
	{
		fail("Not yet implemented");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
	}

}

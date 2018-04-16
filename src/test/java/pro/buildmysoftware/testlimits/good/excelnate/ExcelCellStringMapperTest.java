/**
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.common.testing.NullPointerTester;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapperException;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.mappers.ExcelCellStringMapper;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellStringMapperTest
{

	private ExcelCellStringMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		mapper = new ExcelCellStringMapper();
	}

	@Test
	public void should_MapCellToString() throws Exception
	{
		// given
		Cell cell = cell("  cell content ");

		// when
		String cellStringValue = mapper.map(cell);

		// then
		assertThat(cellStringValue).isEqualTo("cell content");
	}

	@Test
	public void should_MapNumberCellToString() throws Exception
	{
		// given
		Cell cell = numberCell(80057);
		String expectedResult = "80057";

		// when
		String result = mapper.map(cell);

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(ExcelCellStringMapper.class);
		tester.testAllPublicInstanceMethods(mapper);
	}

	@Test
	public void should_ThrowException_When_CellIsBlank() throws Exception
	{
		// given
		Cell cell = blankCell();

		// when
		assertThatThrownBy(() -> mapper.map(cell))
			.isInstanceOf(ExcelCellMapperException.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception
	{
	}

	/**
	 * @return
	 */
	private Cell blankCell()
	{
		return CellFixtureUtils.blankCell();
	}

	/**
	 * @param content
	 * @return
	 * @throws IOException
	 */
	private Cell cell(String content)
	{
		return CellFixtureUtils.stringCell(content);
	}

	/**
	 * @param value
	 * @return
	 * @throws IOException
	 */
	private Cell numberCell(double value)
	{
		return CellFixtureUtils.numberCell(value);
	}

}

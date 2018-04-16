/**
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.common.testing.NullPointerTester;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapper;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapperException;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.mappers.ExcelCellDoubleMapper;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class ExcelCellDoubleMapperTest
{

	private ExcelCellMapper<Double> mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception
	{
		mapper = new ExcelCellDoubleMapper();
	}

	@Test
	public void should_MapCellToDouble() throws Exception
	{
		// given
		double expecteNumber = 100.99;
		Cell cell = numberCell(expecteNumber);

		// when
		double number = mapper.map(cell);

		// then
		assertThat(number).isEqualTo(expecteNumber);
	}

	@Test
	public void should_Pass_NullTests() throws Exception
	{
		NullPointerTester tester = new NullPointerTester();
		tester.testAllPublicConstructors(ExcelCellDoubleMapper.class);
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

	public void should_ThrowException_When_CellIsString() throws Exception
	{
		// given
		Cell cell = stringCell();

		// when
		assertThatThrownBy(() -> mapper.map(cell))
			.isInstanceOf(ExcelCellMapperException.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
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
	 * @param number
	 * @return
	 */
	private Cell numberCell(double number)
	{
		return CellFixtureUtils.numberCell(number);
	}

	/**
	 * @return
	 */
	private Cell stringCell()
	{
		return CellFixtureUtils.stringCell("some content");
	}

}

/**
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate.testobjects;

import pro.buildmysoftware.testlimits.good.excelnate.SourceDocuments;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCell;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelSource;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.mappers.ExcelCellStringMapper;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.row.ExcelObject;

/**
 * @author goobar
 *
 */
public class TestNestedObject
{

	@ExcelObject
	private TestNestedObject2 complexField2;

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;

	/**
	 * @return the complexField2
	 */
	public TestNestedObject2 getComplexField2()
	{
		return complexField2;
	}

	/**
	 * @return the stringField
	 */
	public String getStringField()
	{
		return stringField;
	}

	/**
	 * @param complexField2
	 *                the complexField2 to set
	 */
	public void setComplexField2(TestNestedObject2 complexField2)
	{
		this.complexField2 = complexField2;
	}

	/**
	 * @param stringField
	 *                the stringField to set
	 */
	public void setStringField(String stringField)
	{
		this.stringField = stringField;
	}
}

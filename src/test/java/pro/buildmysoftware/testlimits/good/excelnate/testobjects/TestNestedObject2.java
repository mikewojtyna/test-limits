/**
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate.testobjects;

import pro.buildmysoftware.testlimits.good.excelnate.SourceDocuments;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCell;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelSource;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.mappers.ExcelCellStringMapper;

/**
 * @author goobar
 *
 */
public class TestNestedObject2
{
	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 1,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String stringField;

	/**
	 * @return the stringField
	 */
	public String getStringField()
	{
		return stringField;
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

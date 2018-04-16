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
public class TestObject2
{

	@ExcelCell(cellMapper = ExcelCellStringMapper.class,
		sources = { @ExcelSource(index = 0,
			source = SourceDocuments.DEFAULT_SOURCE_DOCUMENT) })
	private String field;

	/**
	 * @return the field
	 */
	public String getField()
	{
		return field;
	}

}

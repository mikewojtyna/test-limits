/**
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.mappers;

import org.apache.poi.ss.usermodel.Cell;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapper;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapperException;

/**
 * A handy utility class to create exceptions for {@link ExcelCellMapper}s.
 *
 * @author goobar
 *
 */
class ExcelCellMapperExceptionUtils
{

	static ExcelCellMapperException createCellTypeNotSupportedException(
		Cell cell, Class<?> targetType)
	{
		return new ExcelCellMapperException(
			ExcelCellMapperExceptionMessageBuilder
				.buildExceptionCellNotSupportedMsg(cell,
					targetType));
	}

	static ExcelCellMapperException wrapException(Cell cell,
		Class<?> targetType, RuntimeException e)
	{
		return new ExcelCellMapperException(
			ExcelCellMapperExceptionMessageBuilder
				.buildExceptionMsg(cell, targetType, e),
			e);
	}
}

/**
 *
 */
/**
 * The mapping subsystem used by the document reader. Cell mappers are designed
 * to be easily extended. If you need to map specialized cell types, you should
 * take a look at {@link pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapper}
 * interface. Most probably there's no need to provide custom implementations of
 * row mapper, but if that's the case,
 * {@link pro.buildmysoftware.testlimits.good.excelnate.mapping.row.ExcelRowMapper} is the interface
 * which should be implemented.
 *
 * @author goobar
 *
 */
package pro.buildmysoftware.testlimits.good.excelnate.mapping;
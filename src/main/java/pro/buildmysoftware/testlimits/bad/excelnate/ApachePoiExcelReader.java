/**
 *
 */
package pro.buildmysoftware.testlimits.bad.excelnate;

import static com.google.common.base.Preconditions.checkNotNull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCell;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapper;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelCellMapperException;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.cell.ExcelSource;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.row.ExcelObject;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.row.ExcelRowMapper;
import pro.buildmysoftware.testlimits.good.excelnate.mapping.row.ExcelRowMapperException;
import pro.buildmysoftware.testlimits.good.excelnate.reader.ExcelDocument;
import pro.buildmysoftware.testlimits.good.excelnate.reader.ExcelReader;
import pro.buildmysoftware.testlimits.good.excelnate.reader.ExcelReaderException;

/**
 * This version is really bad - it totally ignores SRP. Everything is coupled
 * together. Imagine e.g. adding new cell mapping type - we need to modify this
 * class every time. If we change the strategy of mapping rows (e.g. using an
 * external service), we need to modify this class too. I cannot even imagine
 * how to test that. If we would like to change the ExcelReader implementation
 * to map rows in parallel, we would be forced again to modify the same class.
 * In case of "good" design, we would just need to change the reader (row mapper
 * wouldn't need to be changed at all, the same with cells).
 *
 */
@SuppressWarnings("javadoc")
public class ApachePoiExcelReader<T> implements ExcelReader<T>
{
	/**
	 * @param rowMapper
	 *                a row mapper strategy
	 * @param ignoredRows
	 *                an array of rows (indexed from 0) to be ignored
	 *                (useful e.g. to ignore the header row)
	 * @throws NullPointerException
	 *                 if any argument is null
	 * @return the instance of reader
	 */
	public static <T> ApachePoiExcelReader<T> instance(
		ExcelRowMapper<T> rowMapper, Class<T> type,
		String sourceDocument, Integer... ignoredRows)
		throws NullPointerException
	{
		return new ApachePoiExcelReader<>(type, sourceDocument,
			ignoredRows);
	}

	private final List<Integer> ignoredRows;

	private final String sourceDocument;

	private final Class<T> type;

	/**
	 * @param rowMapper
	 *                a row mapper strategy
	 * @throws NullPointerException
	 *                 if any argument is null
	 */
	private ApachePoiExcelReader(Class<T> type, String sourceDocument,
		Integer... ignoredRows) throws NullPointerException
	{
		this.type = type;
		this.sourceDocument = sourceDocument;
		this.ignoredRows = Arrays.asList(ignoredRows);
	}

	/* (non-Javadoc)
	 * @see com.onezerobinary.certify.excel.reader.ExcelReader#read(com.onezerobinary.certify.excel.reader.ExcelDocument)
	 */
	@Override
	public List<T> read(ExcelDocument excelDocument)
		throws NullPointerException, ExcelReaderException
	{
		try (Workbook workbook = WorkbookFactory.create(
			new ByteArrayInputStream(excelDocument.getContent())))
		{
			return readWorkbook(workbook);
		}
		catch (ExcelRowMapperException | EncryptedDocumentException
			| InvalidFormatException | IOException e)
		{
			throw new ExcelReaderException(MessageFormat.format(
				"Failed to read Excel document: {0}.",
				excelDocument), e);
		}
	}

	/**
	 * @param field
	 * @return
	 */
	private String capitalizeFieldName(Field field)
	{
		return StringUtils.capitalize(field.getName());
	}

	/**
	 * @param prefix
	 * @param field
	 * @return
	 */
	private String fieldMethodName(String prefix, Field field)
	{
		return prefix + capitalizeFieldName(field);
	}

	/**
	 * @param document
	 * @param excelCell
	 * @return
	 */
	private int getCellIndex(String sourceDocument, ExcelCell excelCell)
	{
		ExcelSource[] sources = excelCell.sources();
		Optional<ExcelSource> matchingMapping = Stream.of(sources)
			.filter(s -> sourceDocument.equals(s.source()))
			.findFirst();
		return matchingMapping.map(ExcelSource::index).orElse(-1);
	}

	/**
	 * @param cellIndex
	 * @param row
	 * @param cellMapperClass
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ExcelCellMapperException
	 * @throws NullPointerException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	private Object getCellValue(int cellIndex, Row row,
		Class<? extends ExcelCellMapper<?>> cellMapperClass)
		throws NullPointerException, ExcelCellMapperException,
		InstantiationException, IllegalAccessException,
		IllegalArgumentException, InvocationTargetException,
		NoSuchMethodException, SecurityException
	{
		if (cellIndex < 0)
		{
			return null;
		}
		Object cellValue;
		try
		{
			cellValue = mapCell(row.getCell(cellIndex,
				MissingCellPolicy.CREATE_NULL_AS_BLANK));
		}
		catch (ExcelCellMapperException e)
		{
			return null;
		}
		return cellValue;
	}

	/**
	 * @param rowNum
	 * @return
	 */
	private T instance(int rowNum)
	{
		try
		{
			return type.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			throw new ExcelRowMapperException(MessageFormat.format(
				"Failed to map row {0} to object of type {1}. Failed to instantiate an empty object.",
				rowNum, type), e);
		}
	}

	/**
	 * @param cell
	 * @return
	 */
	private Object mapCell(Cell cell)
	{
		switch (cell.getCellTypeEnum())
		{
			case NUMERIC:
				return new ExcelCellLocalDateMapper().map(cell);

			default:
				return new ExcelCellStringMapper().map(cell);
		}
	}

	/**
	 * Maps cells from Excel row to data object.
	 *
	 * @param object
	 *                Object that contains fields annotated with
	 *                {@link ExcelCell}. This object will have all cell
	 *                fields set with values taken from the corresponding
	 *                Excel cells in the {@code row}. If object doesn't have
	 *                any cell fields, then this method does nothing.
	 * @param row
	 *                row from Excel document containing data that will be
	 *                mapped to {@code object}
	 * @param document
	 *                source document type
	 */
	private void mapCellsFromRow(Object object, Row row, String document)
	{
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			mapField(field, object, row, document);
		}
	}

	/**
	 * @param field
	 * @param document
	 * @param row
	 */
	private void mapField(Field field, Object object, Row row,
		String document)
	{
		try
		{
			ExcelObject excelObjectAnnotation = field
				.getAnnotation(ExcelObject.class);
			if (excelObjectAnnotation != null)
			{
				// process data object recursively
				Object complexObjectInstance = field.getType()
					.newInstance();
				mapCellsFromRow(complexObjectInstance, row,
					document);
				setField(field, complexObjectInstance, object);
				return;
			}
			ExcelCell excelCellAnnotation = field
				.getAnnotation(ExcelCell.class);
			if (excelCellAnnotation == null)
			{
				return;
			}
			Object cellValue = getCellValue(
				getCellIndex(document, excelCellAnnotation),
				row, excelCellAnnotation.cellMapper());
			if (cellValue != null)
			{
				setField(field, cellValue, object);
			}
		}
		catch (InstantiationException | IllegalAccessException
			| IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException e)
		{
			throw new ExcelRowMapperException(MessageFormat.format(
				"Mapping Excel cell to field {0} in object {1} failed.",
				field, object), e);
		}
	}

	/**
	 * @param row
	 * @return
	 */
	private T mapRow(Row row)
	{
		validate(row);
		T instance = instance(row.getRowNum());
		mapCellsFromRow(instance, row, sourceDocument);
		return instance;
	}

	/**
	 * @param workbook
	 * @return
	 */
	private List<T> readWorkbook(Workbook workbook)
	{
		List<T> result = new ArrayList<>();
		Sheet sheet = workbook.getSheetAt(0);
		boolean ignoreLastRow = ignoredRows.contains(-1);
		int lastRowNum = sheet.getLastRowNum();
		for (Row row : sheet)
		{
			// do not process rows which should be ignored
			if (!ignoredRows.contains(row.getRowNum()))
			{
				// -1 row number means the last row - if
				// current row is the last row and last
				// row should be ignored - do not
				// process
				if (!(ignoreLastRow
					&& row.getRowNum() == lastRowNum))
				{
					result.add(mapRow(row));
				}
			}
		}
		return result;
	}

	/**
	 * @param field
	 * @param object
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *
	 */
	private void setField(Field field, Object value, Object object)
		throws NoSuchMethodException, SecurityException,
		IllegalAccessException, IllegalArgumentException,
		InvocationTargetException
	{
		setter(field, object).invoke(object, value);
	}

	/**
	 * @param field
	 * @param object
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private Method setter(Field field, Object object)
		throws NoSuchMethodException, SecurityException
	{
		return object.getClass().getMethod(
			fieldMethodName("set", field), field.getType());
	}

	/**
	 * @param row
	 */
	private void validate(Row row)
	{
		checkNotNull(row, "'row' cannot be null");
	}

	private class ExcelCellLocalDateMapper
		implements ExcelCellMapper<LocalDate>
	{

		/* (non-Javadoc)
		 * @see com.onezerobinary.certify.excel.reader.mapping.ExcelCellMapper#map(org.apache.poi.ss.usermodel.Cell)
		 */
		@Override
		public LocalDate map(Cell cell)
			throws NullPointerException, ExcelCellMapperException
		{
			validate(cell);
			try
			{
				switch (cell.getCellTypeEnum())
				{
					case NUMERIC:
						return convertCellToLocalDate(
							cell);

					default:
						throw createCellTypeNotSupportedException(
							cell);
				}

			}
			catch (IllegalStateException | NumberFormatException e)
			{
				throw wrapException(cell, e);
			}
		}

		/**
		 * @param cell
		 * @return
		 */
		private LocalDate convertCellToLocalDate(Cell cell)
		{
			return cell.getDateCellValue().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		}

		/**
		 * @param cell
		 * @return
		 */
		private ExcelCellMapperException createCellTypeNotSupportedException(
			Cell cell)
		{
			return ExcelCellMapperExceptionUtils
				.createCellTypeNotSupportedException(cell,
					LocalDate.class);
		}

		/**
		 * @param cell
		 */
		private void validate(Cell cell)
		{
			checkNotNull(cell, "'cell' cannot be null");
		}

		/**
		 * @param cell
		 * @param e
		 * @return
		 */
		private ExcelCellMapperException wrapException(Cell cell,
			RuntimeException e)
		{
			return ExcelCellMapperExceptionUtils.wrapException(cell,
				LocalDate.class, e);
		}

	}

	private class ExcelCellStringMapper implements ExcelCellMapper<String>
	{

		/**
		 * Returns the string value of this cell without leading and
		 * trailing whitespaces. This method automatically converts
		 * number values.
		 */
		@Override
		public String map(Cell cell)
			throws NullPointerException, ExcelCellMapperException
		{
			validate(cell);
			switch (cell.getCellTypeEnum())
			{
				case STRING:
					return cell.getStringCellValue().trim();
				case NUMERIC:
					return convertNumericToString(cell);
				default:
					throw new ExcelCellMapperException(
						ExcelCellMapperExceptionMessageBuilder
							.buildExceptionCellNotSupportedMsg(
								cell,
								String.class));
			}
		}

		/**
		 * @param cell
		 * @return
		 */
		private String convertNumericToString(Cell cell)
		{
			return new DataFormatter().formatCellValue(cell);
		}

		/**
		 * @param cell
		 */
		private void validate(Cell cell)
		{
			checkNotNull(cell, "'cell' cannot be null");
		}

	}
}

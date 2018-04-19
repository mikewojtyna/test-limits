/**
 *
 */
package pro.buildmysoftware.testlimits.good;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.good.Product.Discount;

/**
 * @author goobar
 *
 */
@SuppressWarnings("unused")
class ProductTestTemplate
{

	/**
	 * @param value
	 * @return
	 */
	private Discount discount(BigDecimal value)
	{
		Discount mock = mock(Discount.class);
		when(mock.calculate()).thenReturn(value);
		return mock;
	}

	/**
	 * @param value
	 * @return
	 */
	private BigDecimal price(double value)
	{
		return BigDecimal.valueOf(value).setScale(2);
	}

	/**
	 * @param price
	 */
	private Product productWithPrice(BigDecimal price)
	{
		return new Product(randomId(), randomName(), price,
			randomQuantity());
	}

	/**
	 * @param price
	 * @param quantity
	 * @return
	 */
	private Product productWithPriceAndQuantity(BigDecimal price,
		int quantity)
	{
		return new Product(randomId(), randomName(), price, quantity);
	}

	/**
	 * @return
	 */
	private String randomId()
	{
		return UUID.randomUUID().toString();
	}

	/**
	 * @return
	 */
	private String randomName()
	{
		return RandomStringUtils.randomAlphabetic(10);
	}

	/**
	 * @return
	 */
	private int randomQuantity()
	{
		return new Random().nextInt(1000);
	}

	@DisplayName("should apply discount")
	@Test
	void applyDiscount() throws Exception
	{
		// given

		// when

		// then
	}

	@DisplayName("should calculate total cost after discount")
	@Test
	void totalCostAfterDiscount() throws Exception
	{
		// given

		// when

		// then
	}

	@DisplayName("should calculate total cost without discount")
	@Test
	void totalCostBeforeDiscount() throws Exception
	{
		// given

		// when

		// then
	}

}

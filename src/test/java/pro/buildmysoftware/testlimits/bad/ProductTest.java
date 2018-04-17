/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.bad.Product.Discount;

/**
 * @author goobar
 *
 */
class ProductTest
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
		Product product = productWithPrice(price(39.99));

		// when
		Product productAfterDiscount = product
			.apply(discount(price(10.00)));

		// then
		assertThat(productAfterDiscount.getPrice())
			.isEqualTo(price(29.99));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
	}

	@DisplayName("should calculate total cost after discount")
	@Test
	void totalCostAfterDiscount() throws Exception
	{
		// given
		Product product = productWithPriceAndQuantity(price(39.99), 10)
			.apply(discount(price(10.00)));

		// when
		BigDecimal totalCost = product.totalCost();

		// then
		assertThat(totalCost).isEqualTo(price(299.90));
		fail("Not yet implemented");
	}

	@DisplayName("should calculate total cost without discount")
	@Test
	void totalCostBeforeDiscount() throws Exception
	{
		// given
		Product product = productWithPriceAndQuantity(price(99.99), 10);

		// when
		BigDecimal totalCost = product.totalCost();

		// then
		assertThat(totalCost).isEqualTo(price(999.90));
	}

}

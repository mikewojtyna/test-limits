/**
 *
 */
package pro.buildmysoftware.testlimits;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author goobar
 *
 */
public class ItemPriceFinder
{
	private final Collection<Item> items = Arrays.asList(
		new Item("Rocket launcher", BigDecimal.valueOf(1_000_000)),
		new Item("Teddy bear", BigDecimal.valueOf(20)),
		new Item("Bike", BigDecimal.valueOf(1_000)));

	/**
	 * Returns price of the item. If item doesn't exist, big decimal with
	 * zero value is returned.
	 *
	 * @param itemName
	 *                name of the item
	 * @return price of the item, or {@link BigDecimal#ZERO}
	 */
	public BigDecimal findPrice(String itemName)
	{
		return items.stream().filter(i -> i.getName().equals(itemName))
			.map(Item::getPrice).findAny().get();
	}

	/**
	 * @author goobar
	 *
	 */
	private static class Item
	{

		private final String name;

		private final BigDecimal price;

		/**
		 * @param price
		 *
		 */
		private Item(String name, BigDecimal price)
		{
			this.name = name;
			this.price = price;
		}

		/**
		 * @return the price
		 */
		public BigDecimal getPrice()
		{
			return price;
		}

		/**
		 * @return the name
		 */
		private String getName()
		{
			return name;
		}
	}
}

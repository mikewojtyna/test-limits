/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import java.util.Collection;

/**
 * @author goobar
 *
 */
class ItemFinder
{
	private Collection<Item> items;

	Item findByName(String name)
	{
		return items.stream().filter(i -> i.getName().equals(name))
			.findAny().get();
	}

	/**
	 * @author goobar
	 *
	 */
	static class Item
	{
		static Item withName(String name)
		{
			return new Item(name);
		}

		private final String name;

		/**
		 *
		 */
		private Item(String name)
		{
			this.name = name;
		}

		/**
		 * @return the name
		 */
		String getName()
		{
			return name;
		}
	}
}

/**
 *
 */
package pro.buildmysoftware.testlimits.good;

import java.math.BigDecimal;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class Product
{
	private String id;

	private String name;

	private BigDecimal price;

	private int quantity;

	public Product(String id, String name, BigDecimal price, int quantity)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	private Product()
	{
		id = null;
		name = null;
		price = null;
		quantity = 0;
	}

	/**
	 * Applies a discount to this product. This method doesn't change state
	 * of the product. Instead, a new product (with applied discount) is
	 * returned. <b>Important note:</b> the problem with this method is that
	 * there's also a hidden contract. Namely, that the returned product "is
	 * the same except of applied discount as the original product".
	 * Unfortunately, it's not so easy to verify this behavior in tests.
	 * Think for a while: how do you know that the new product is "the same"
	 * but with applied discount without getting into implementation details
	 * (e.g. without checking private properties)?
	 *
	 *
	 * @param discount
	 *                a discount to apply
	 * @return the product with applied discount
	 */
	public Product apply(Discount discount)
	{
		Product productAfterDiscount = new Product();
		productAfterDiscount.id = id;
		productAfterDiscount.price = price
			.subtract(discount.calculate());
		productAfterDiscount.name = name;
		productAfterDiscount.quantity = quantity;
		return productAfterDiscount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		Product other = (Product) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else if (!id.equals(other.id))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Product [id=" + id + ", name=" + name + "]";
	}

	public BigDecimal totalCost()
	{
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public static interface Discount
	{
		BigDecimal calculate();
	}
}

/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

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

	public Product apply(Discount discount)
	{
		Product productAfterDiscount = new Product();
		productAfterDiscount.id = id;
		productAfterDiscount.price = discount.calculate();
		productAfterDiscount.name = name;
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

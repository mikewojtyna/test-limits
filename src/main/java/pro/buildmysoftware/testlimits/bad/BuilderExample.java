/**
 *
 */
package pro.buildmysoftware.testlimits.bad;

import java.util.Optional;
import javax.annotation.Generated;

/**
 * A builder example. Generally, unit testing builders might require a lot of
 * work, especially when there are a lot of optional properties. However, most
 * builder code is a typical boilerplate. Therefore, there's a natural question:
 * should we test that manually? We are generating most of the code anyway.
 *
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public class BuilderExample
{
	/**
	 * Creates builder to build {@link BuilderExample}.
	 *
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder()
	{
		return new Builder();
	}

	private final int age;

	private final int height;

	private final String name;

	@Generated("SparkTools")
	private BuilderExample(Builder builder)
	{
		age = builder.age;
		height = builder.height;
		name = builder.name;
	}

	/**
	 * @return the age
	 */
	public Optional<Integer> getAge()
	{
		return Optional.ofNullable(age);
	}

	/**
	 * @return the height
	 */
	public Optional<Integer> getHeight()
	{
		return Optional.ofNullable(height);
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Builder to build {@link BuilderExample}.
	 */
	@Generated("SparkTools")
	public static final class Builder
	{
		private int age;

		private int height;

		private String name;

		private Builder()
		{
		}

		public BuilderExample build()
		{
			return new BuilderExample(this);
		}

		public Builder withAge(int age)
		{
			this.age = age;
			return this;
		}

		public Builder withHeight(int height)
		{
			this.height = height;
			return this;
		}

		public Builder withName(String name)
		{
			this.name = name;
			return this;
		}
	}

}

/**
 *
 */
package pro.buildmysoftware.testlimits;

import static org.assertj.core.api.Assertions.assertThat;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.BuilderExample.Builder;

/**
 * @author goobar
 *
 */
class BuilderExampleTest
{

	/**
	 * @return
	 */
	private String anyName()
	{
		return RandomStringUtils.randomAlphabetic(6);
	}

	/**
	 * @return
	 *
	 */
	private Builder builder()
	{
		return BuilderExample.builder();
	}

	/**
	 * @return
	 */
	private BuilderExample buildWithoutOptionals()
	{
		return BuilderExample.builder().withName(anyName()).build();
	}

	@DisplayName("should initialize all properties")
	@Test
	void allProperties() throws Exception
	{
		// when
		BuilderExample example = builder().withName("george")
			.withAge(10).withHeight(180).build();

		// then
		assertThat(example.getName()).isEqualTo("george");
		assertThat(example.getHeight().get()).isEqualTo(180);
		assertThat(example.getAge().get()).isEqualByComparingTo(10);
	}

	@DisplayName("should not initialize optional properties when not given as parameters")
	@Test
	void optionalProperties() throws Exception
	{
		// when
		BuilderExample instance = buildWithoutOptionals();

		// then
		assertThat(instance.getAge().isPresent()).isFalse();
		assertThat(instance.getHeight().isPresent()).isFalse();
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

}

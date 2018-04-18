/**
 *
 */
package pro.buildmysoftware.testlimits.bad.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author goobar
 *
 */
@SpringBootApplication
public class Application
{
	@SuppressWarnings("javadoc")
	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(ResidentsController.class, args);
	}
}

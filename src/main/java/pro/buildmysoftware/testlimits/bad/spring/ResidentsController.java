/**
 *
 */
package pro.buildmysoftware.testlimits.bad.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author goobar
 *
 */
@RestController
public class ResidentsController
{

	private ResidentsService residentsService;

	/**
	 * @return users loaded by user service
	 */
	@GetMapping(value = "/castle-black/residents")
	public Iterable<String> castleBlackResidents()
	{
		return residentsService.loadFromCastleBlack();
	}

}

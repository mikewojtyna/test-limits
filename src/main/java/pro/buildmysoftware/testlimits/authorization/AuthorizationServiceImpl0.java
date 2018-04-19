/**
 *
 */
package pro.buildmysoftware.testlimits.authorization;

/**
 * @author goobar
 *
 */
public class AuthorizationServiceImpl0 implements AuthorizationService
{
	private final UserRepository userRepository;

	@SuppressWarnings("javadoc")
	public AuthorizationServiceImpl0(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public boolean isAuthorized(String username, String permission)
	{
		return userRepository.findByName(username)
			.hasPermission(permission);
	}

}

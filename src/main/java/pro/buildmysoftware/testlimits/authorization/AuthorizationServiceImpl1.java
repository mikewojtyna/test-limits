/**
 *
 */
package pro.buildmysoftware.testlimits.authorization;

/**
 * @author goobar
 *
 */
public class AuthorizationServiceImpl1 implements AuthorizationService
{

	private final UserRepository userRepository;

	@SuppressWarnings("javadoc")
	public AuthorizationServiceImpl1(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	/* (non-Javadoc)
	 * @see pro.buildmysoftware.testlimits.authorization.AuthorizationService#isAuthorized(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isAuthorized(String username, String permission)
	{
		return userRepository.findAll().stream()
			.filter(u -> username.equals(u.getUsername())).findAny()
			.map(u -> u.hasPermission(permission)).orElse(false);
	}

}

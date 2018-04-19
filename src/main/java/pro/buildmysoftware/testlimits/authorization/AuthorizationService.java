/**
 *
 */
package pro.buildmysoftware.testlimits.authorization;

/**
 * @author goobar
 *
 */
public interface AuthorizationService
{
	/**
	 * Checks whether user has permission to perform an action.
	 *
	 * @param username
	 *                a username
	 * @param permission
	 *                a required permission
	 * @return {@code true} if user has permission, {@code false} otherwise
	 */
	boolean isAuthorized(String username, String permission);
}

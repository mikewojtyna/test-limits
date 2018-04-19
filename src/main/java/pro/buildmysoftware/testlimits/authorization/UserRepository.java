/**
 *
 */
package pro.buildmysoftware.testlimits.authorization;

import java.util.Set;

/**
 * @author goobar
 *
 */
@SuppressWarnings("javadoc")
public interface UserRepository
{

	Set<User> findAll();

	User findByName(String username);

	class User
	{
		private Set<String> permissions;

		private String username;

		/**
		 * @return the username
		 */
		public String getUsername()
		{
			return username;
		}

		public boolean hasPermission(String permission)
		{
			return permissions.contains(permission);
		}
	}
}

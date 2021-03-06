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
		private final Set<String> permissions;

		private final String username;

		public User(String username, Set<String> permissions)
		{
			this.username = username;
			this.permissions = permissions;
		}

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

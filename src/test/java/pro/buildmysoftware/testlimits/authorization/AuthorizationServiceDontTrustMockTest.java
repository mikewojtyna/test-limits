/**
 *
 */
package pro.buildmysoftware.testlimits.authorization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.buildmysoftware.testlimits.authorization.UserRepository.User;

/**
 * @author goobar
 *
 */
class AuthorizationServiceDontTrustMockTest
{

	/**
	 * @param arya
	 * @return
	 */
	private UserRepository inMemoryRepo(User arya)
	{
		return new UserRepository()
		{

			@Override
			public Set<User> findAll()
			{
				return Collections.singleton(arya);
			}

			@Override
			public User findByName(String username)
			{
				return arya;
			}
		};
	}

	/**
	 * @param username
	 * @param permission
	 * @return
	 */
	private User user(String username, String permission)
	{
		return new User(username, Collections.singleton(permission));
	}

	@DisplayName("should authorize user using AuthorizationServiceImpl0 and \"correct\" mock")
	@Test
	void authorize0() throws Exception
	{
		// given
		// create user Arya
		String username = "Arya";
		String permission = "vengeance";
		User arya = user(username, permission);
		// mock the repository
		UserRepository repository = mock(UserRepository.class);
		when(repository.findByName(username)).thenReturn(arya);
		// configure the authorization service to use mock
		AuthorizationService authorizationService = new AuthorizationServiceImpl0(
			repository);

		// when
		boolean authorized = authorizationService.isAuthorized(username,
			permission);

		// then
		assertThat(authorized).isTrue();
	}

	/**
	 * Except of using other AuthorizationService implementation, this test
	 * is exactly the same as {@link #authorize1InMemory()}. Our tests are
	 * no longer brittle and don't depend on the specifics of implementation
	 * - thanks to using in-memory repository fake.
	 *
	 * @throws Exception
	 */
	@DisplayName("should authorize user using AuthorizationServiceImpl0 and in-memory repository")
	@Test
	void authorize0InMemory() throws Exception
	{
		// given
		// create user Arya
		String username = "Arya";
		String permission = "vengeance";
		User arya = user(username, permission);
		// user in-memory implementation of repository
		UserRepository repository = inMemoryRepo(arya);
		// configure the authorization service to use in-memory
		// repository - except of this line this test is exactly the
		// same as authorize1InMemory()
		AuthorizationService authorizationService = new AuthorizationServiceImpl0(
			repository);

		// when
		boolean authorized = authorizationService.isAuthorized(username,
			permission);

		// then
		assertThat(authorized).isTrue();
	}

	@DisplayName("should authorize user using AuthorizationServiceImpl1 and \"incorrect\" mock")
	@Test
	void authorize1() throws Exception
	{
		// given
		// create user Arya
		String username = "Arya";
		String permission = "vengeance";
		User arya = user(username, permission);
		// mock the repository
		UserRepository repository = mock(UserRepository.class);
		when(repository.findByName(username)).thenReturn(arya);
		// configure the authorization service to use mock
		AuthorizationService authorizationService = new AuthorizationServiceImpl1(
			repository);

		// when
		boolean authorized = authorizationService.isAuthorized(username,
			permission);

		// then
		// this doesn't work - why?
		assertThat(authorized).isTrue();
	}

	/**
	 * Except of using other AuthorizationService implementation, this test
	 * is exactly the same as {@link #authorize0InMemory()}. Our tests are
	 * no longer brittle and don't depend on the specifics of implementation
	 * - thanks to using in-memory repository fake.
	 *
	 * @throws Exception
	 */
	@DisplayName("should authorize user using AuthorizationServiceImpl1 and in-memory repository")
	@Test
	void authorize1InMemory() throws Exception
	{
		// given
		// create user Arya
		String username = "Arya";
		String permission = "vengeance";
		User arya = user(username, permission);
		// user in-memory implementation of repository
		UserRepository repository = inMemoryRepo(arya);
		// configure the authorization service to use in-memory
		// repository - except of this line this test is exactly the
		// same as authorize0InMemory()
		AuthorizationService authorizationService = new AuthorizationServiceImpl1(
			repository);

		// when
		boolean authorized = authorizationService.isAuthorized(username,
			permission);

		// then
		assertThat(authorized).isTrue();
	}

	@DisplayName("should authorize user using AuthorizationServiceImpl1 and \"correct\" mock")
	@Test
	void authorize1Right() throws Exception
	{
		// given
		// create user Arya
		String username = "Arya";
		String permission = "vengeance";
		User arya = user(username, permission);
		// mock the repository
		UserRepository repository = mock(UserRepository.class);
		when(repository.findAll())
			.thenReturn(Collections.singleton(arya));
		// configure the authorization service to use mock
		AuthorizationService authorizationService = new AuthorizationServiceImpl1(
			repository);

		// when
		boolean authorized = authorizationService.isAuthorized(username,
			permission);

		// then
		// Now this works... Is mocking repository really the best
		// option?
		assertThat(authorized).isTrue();
	}

}

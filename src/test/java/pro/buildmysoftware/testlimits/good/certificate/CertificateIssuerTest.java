/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Now, here's a problem. Should we test aggregate as a whole, or each part of
 * the aggregate separately? Currently the only public element of the entire
 * aggregate is CertificateIssuer, so it's a hint other components are
 * implementation details of the aggregate and therefore should not be tested
 * separately. However, this might lead to a bit cumbersome design, where every
 * component is too rigid and thus testability is limited. Testing edge cases
 * might be problematic too. Also, our model might (and will) evolve in the
 * future. It might happen that e.g. certificate becomes aggregate on this own
 * or might be a shared entity (used by multiple aggregates). In such case, it
 * wouldn't be practical to duplicate tests in every aggregate invoking
 * certificate's methods. <br/>
 * <strong>It's a controversial problem. What's the responsibility of an
 * aggregate? Only delegation to value objects/entities, or maybe full business
 * logic? You will find advocates of both methods, and each side has its
 * merits.</strong>
 *
 */
class CertificateIssuerTest
{

	/**
	 * Performs a test using an aggregate. This is more like a "mini
	 * integration test". This test slowly starts to become a mess, and as
	 * such is a candidate to @Disable. Developers will likely stop writing
	 * tests for this aggregate at all. Even if this design might evolve to
	 * something good (or even might be close to be good), the vision of
	 * responsibilities is obscured.
	 *
	 * @throws Exception
	 */
	@DisplayName("should issue new certificate using aggregate")
	@Test
	void testIssuer() throws Exception
	{
		// given
		// See how big this block is?
		CertificateIssuer issuer = new CertificateIssuer();
		String ownerName = "certificateOwner";
		CertificateOwnerRepository repo = mock(
			CertificateOwnerRepository.class);
		CertificateOwner owner = new CertificateOwner();
		when(repo.findByName(ownerName)).thenReturn(owner);
		Approver approver = new Approver(repo);

		// when
		// Now, this throws an exception because Certificate status has
		// illegal state. We would need another preparations in the
		// given block. We would avoid that when testing each component
		// separately. However, it's good to leave this as it is
		// because it shows the problem well.
		issuer.issueDraftFor(ownerName, approver);

		// then
		assertThat(owner.getCertificates()).hasSize(1);
		// TODO: should also test for status
	}

}

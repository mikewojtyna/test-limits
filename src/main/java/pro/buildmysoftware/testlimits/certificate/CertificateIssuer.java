/**
 *
 */
package pro.buildmysoftware.testlimits.certificate;

/**
 * Represents an authority to issue certificates.
 *
 * @author goobar
 *
 */
public class CertificateIssuer
{
	/**
	 * Issues a new certificate draft. This is not a real certificate, but
	 * rather a "certificate proposition" that will be send later to
	 * authority responsible for issuing real certificates.
	 *
	 * @param certificateOwner
	 *                owner for which this draft will be issued
	 * @param approver
	 *                the authority to approve certificate drafts
	 */
	public void issueDraftFor(String certificateOwner, Approver approver)
	{
		approver.approveDraft(new CertificateDraft(Certificate.issue(),
			certificateOwner));
	}
}

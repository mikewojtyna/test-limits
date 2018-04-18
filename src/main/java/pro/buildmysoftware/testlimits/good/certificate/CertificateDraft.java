/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

/**
 * @author goobar
 *
 */
class CertificateDraft
{
	private final Certificate certificate;

	private final String issuedFor;

	CertificateDraft(Certificate certificate, String issuedFor)
	{
		this.certificate = certificate;
		this.issuedFor = issuedFor;
	}

	/**
	 * @return the certificate
	 */
	Certificate getCertificate()
	{
		return certificate;
	}

	/**
	 * @return the issuedFor
	 */
	String getIssuedFor()
	{
		return issuedFor;
	}
}

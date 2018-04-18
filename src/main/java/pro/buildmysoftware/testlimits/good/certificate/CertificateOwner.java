/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

import java.util.Set;
import pro.buildmysoftware.testlimits.good.certificate.Certificate.CertificateStatus;

/**
 * @author goobar
 *
 */
class CertificateOwner
{
	private Set<Certificate> certificates;

	private String name;

	/**
	 * @return the certificates
	 */
	public Set<Certificate> getCertificates()
	{
		return certificates;
	}

	/**
	 * Confirms certificate by the certificating. When certificate is
	 * confirmed, it means the owner of this certificate has gained
	 * competences provided by this certificate.
	 *
	 * @param certificateNumber
	 *                number of the certificate to confirm
	 */
	void confirmCertificate(String certificateNumber)
	{
		certificates.stream()
			.filter(c -> certificateNumber.equals(c.getId()))
			.findAny().ifPresent(c -> c
				.updateStatus(CertificateStatus.CONFIRMED));
	}

	/**
	 * @return the name
	 */
	String getName()
	{
		return name;
	}

	/**
	 * Issues a new certificate for this owner.
	 *
	 * @param certNumber
	 *                certificate number
	 */
	void issueCertificate(String certNumber)
	{
		certificates.add(Certificate.issue(certNumber));
	}
}

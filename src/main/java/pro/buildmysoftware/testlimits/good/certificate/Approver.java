/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

/**
 * @author goobar
 *
 */
class Approver
{
	/**
	 * @param owner
	 * @return
	 */
	private CertificateOwner findInDb(String owner)
	{
		// TODO: add real db query
		return new CertificateOwner();
	}

	/**
	 * @param owner
	 * @return
	 */
	private CertificateOwner findOwner(String owner)
	{
		return findInDb(owner);
	}

	/**
	 * @return
	 */
	private boolean someRules()
	{
		// TODO: add some real business rules
		return true;
	}

	void approveDraft(CertificateDraft draft)
	{
		if (someRules())
		{
			CertificateOwner owner = findOwner(
				draft.getIssuedFor());
			String certNumber = draft.getCertificate().getId();
			owner.issueCertificate(certNumber);
			owner.confirmCertificate(certNumber);
		}
	}
}

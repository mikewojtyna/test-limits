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
	private final CertificateOwnerRepository repository;

	public Approver(CertificateOwnerRepository repository)
	{
		this.repository = repository;
	}

	/**
	 * @param owner
	 * @return
	 */
	private CertificateOwner findInDb(String owner)
	{
		return repository.findByName(owner);
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

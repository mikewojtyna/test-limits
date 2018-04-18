/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

import java.util.UUID;

/**
 * @author goobar
 *
 */
class Certificate
{
	/**
	 * @return
	 */
	static Certificate issue()
	{
		return issue(UUID.randomUUID().toString());
	}

	static Certificate issue(String certNumber)
	{
		return new Certificate(certNumber);
	}

	private final String id;

	private CertificateStatus status;

	/**
	 * @param id
	 *
	 */
	private Certificate(String id)
	{
		this.id = id;
		status = CertificateStatus.ISSUED;
	}

	/**
	 * @param newStatus
	 * @return
	 */
	private boolean statusValid(CertificateStatus newStatus)
	{
		return (status == CertificateStatus.PENDING
			&& newStatus == CertificateStatus.CONFIRMED)
			|| (status == CertificateStatus.ISSUED
				&& newStatus == CertificateStatus.PENDING);
	}

	/**
	 * @return the id
	 */
	String getId()
	{
		return id;
	}

	void updateStatus(CertificateStatus newStatus)
	{
		if (statusValid(newStatus))
		{
			status = newStatus;
		}
		else
		{
			throw new IllegalArgumentException("Status invalid");
		}
	}

	/**
	 * @author goobar
	 *
	 */
	enum CertificateStatus
	{
		CONFIRMED, ISSUED, PENDING
	}
}

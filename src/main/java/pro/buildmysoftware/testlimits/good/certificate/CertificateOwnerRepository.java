/**
 *
 */
package pro.buildmysoftware.testlimits.good.certificate;

/**
 * @author goobar
 *
 */
interface CertificateOwnerRepository
{
	CertificateOwner findByName(String name);
}

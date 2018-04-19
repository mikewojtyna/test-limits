/**
 *
 */
package pro.buildmysoftware.testlimits.certificate;

/**
 * @author goobar
 *
 */
interface CertificateOwnerRepository
{
	CertificateOwner findByName(String name);
}

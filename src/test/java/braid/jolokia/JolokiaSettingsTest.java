package braid.jolokia;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JolokiaSettingsTest {

	private String jolokiaHost = "localhost";
	private String jolokiaPort = "1234";
	private String jolokiaPath = "jolokia";
	private JolokiaSettings jolokiaSettings;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(JolokiaSettings.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() throws Exception {
		jolokiaSettings = new JolokiaSettings(jolokiaHost, jolokiaPort, jolokiaPath);
	}

	@Test
	public void testGetJolokiaHost() {
		String host = jolokiaSettings.getJolokiaHost();
		host = "newhost";
		assertNotEquals(host, jolokiaSettings.getJolokiaHost());
		assertEquals(jolokiaHost, jolokiaSettings.getJolokiaHost());
	}

	@Test
	public void testGetJolokiaPort() {
		String port = jolokiaSettings.getJolokiaPort();
		port = "newport";
		assertNotEquals(port, jolokiaSettings.getJolokiaPort());
		assertEquals(jolokiaPort, jolokiaSettings.getJolokiaPort());
	}

	@Test
	public void testGetJolokiaPath() {
		String path = jolokiaSettings.getJolokiaPath();
		path = "newpath";
		assertNotEquals(path, jolokiaSettings.getJolokiaPath());
		assertEquals(jolokiaPath, jolokiaSettings.getJolokiaPath());
	}

}

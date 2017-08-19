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
public class JolokiaSettingsBuilderTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(JolokiaSettingsBuilder.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuilder() {
		JolokiaSettingsBuilder builder1 = JolokiaSettingsBuilder.builder();
		JolokiaSettingsBuilder builder2 = JolokiaSettingsBuilder.builder();
		assertNotSame(builder1, builder2);
	}

	@Test
	public void testWithJolokiaHost() {
		String host = "newhost";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		JolokiaSettings jolokiaSettings = builder.withJolokiaHost(host).build();
		assertEquals(host, jolokiaSettings.getJolokiaHost());
	}

	@Test
	public void testWithJolokiaPort() {
		String port = "1324";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		JolokiaSettings jolokiaSettings = builder.withJolokiaPort(port).build();
		assertEquals(port, jolokiaSettings.getJolokiaPort());

	}

	@Test
	public void testWithJolokiaPath() {
		String path = "jolokia";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		JolokiaSettings jolokiaSettings = builder.withJolokiaPath(path).build();
		assertEquals(path, jolokiaSettings.getJolokiaPath());
	}

	@Test
	public void testFrom() {
		String host = "local";
		String port = "1234";
		String path = "jolka";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		builder.from(new JolokiaSettings(host, port, path));
		JolokiaSettings jolokiaSettings = builder.build();
		assertEquals(host, jolokiaSettings.getJolokiaHost());
		assertEquals(port, jolokiaSettings.getJolokiaPort());
		assertEquals(path, jolokiaSettings.getJolokiaPath());
	}

	@Test
	public void testBut() {
		String host = "localhost";
		String port = "12345";
		String path = "jolokia";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		builder.withJolokiaHost(host).withJolokiaPort(port).withJolokiaPath(path);
		JolokiaSettingsBuilder builder2 = builder.but();
		JolokiaSettings jolokiaSettings = builder2.build();
		assertEquals(host, jolokiaSettings.getJolokiaHost());
		assertEquals(port, jolokiaSettings.getJolokiaPort());
		assertEquals(path, jolokiaSettings.getJolokiaPath());
		
	}

	@Test
	public void testBuild() {
		String host = "localhost";
		String port = "12345";
		String path = "jolokia";
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		JolokiaSettings jolokiaSettings = builder.withJolokiaHost(host).withJolokiaPort(port).withJolokiaPath(path).build();
		assertEquals(host, jolokiaSettings.getJolokiaHost());
		assertEquals(port, jolokiaSettings.getJolokiaPort());
		assertEquals(path, jolokiaSettings.getJolokiaPath());
	}

}

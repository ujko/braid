package braid.beans.jolokia;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import braid.constants.AppConstants;
import braid.jolokia.JolokiaSettings;

@RunWith(Arquillian.class)
public class JolokiaBeanTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class).addClass(JolokiaBean.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	private JolokiaBean jolokiaBean;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInit() {
		JolokiaBean jolokia = new JolokiaBean();
		jolokia.init();
		assertEquals(AppConstants.JOLOKIA_HOST, jolokia.getJolokiaHost());
		assertEquals(AppConstants.JOLOKIA_PORT, jolokia.getJolokiaPort());
		assertEquals(AppConstants.JOLOKIA_PATH, jolokia.getJolokiaPath());
		assertTrue(jolokia.getRefreshTime()==10);
	}

	@Test
	public void testGetJolokiaHost() {
		String host = "jolokiahost";
		jolokiaBean.setJolokiaHost(host);
		assertEquals(host, jolokiaBean.getJolokiaHost());
	}

	@Test
	public void testSetJolokiaHost() {
		String host = "jolokiahost";
		jolokiaBean.setJolokiaHost(host);
		assertEquals(host, jolokiaBean.getJolokiaHost());
	}

	@Test
	public void testGetJolokiaPort() {
		String port = "jolokiaport";
		jolokiaBean.setJolokiaPort(port);
		assertEquals(port, jolokiaBean.getJolokiaPort());
	}

	@Test
	public void testSetJolokiaPort() {
		String port = "jolokiaport";
		jolokiaBean.setJolokiaPort(port);
		assertEquals(port, jolokiaBean.getJolokiaPort());
	}

	@Test
	public void testGetJolokiaPath() {
		String path = "jolokiapath";
		jolokiaBean.setJolokiaPath(path);
		assertEquals(path, jolokiaBean.getJolokiaPath());
	}

	@Test
	public void testSetJolokiaPath() {
		String path = "jolokiapath";
		jolokiaBean.setJolokiaPath(path);
		assertEquals(path, jolokiaBean.getJolokiaPath());
	}

	@Test
	public void testGetRefreshTime() {
		int refreshTime = 3;
		jolokiaBean.setRefreshTime(refreshTime);;
		assertTrue(refreshTime==jolokiaBean.getRefreshTime());
	}

	@Test
	public void testSetRefreshTime() {
		int refreshTime = 112;
		jolokiaBean.setRefreshTime(refreshTime);;
		assertTrue(refreshTime==jolokiaBean.getRefreshTime());

	}

	@Test
	public void testConnect() {
		assertEquals("braid", jolokiaBean.connect());
	}

	@Test
	public void testBuildJolokiaSettings() {
		String host = "localJolokiaHost";
		String port = "1234";
		String path = "localJolokiaPath";
		
		jolokiaBean.setJolokiaHost(host);
		jolokiaBean.setJolokiaPort(port);
		jolokiaBean.setJolokiaPath(path);
		
		JolokiaSettings jolokiaSettings = jolokiaBean.buildJolokiaSettings();
		
		assertEquals(host, jolokiaSettings.getJolokiaHost());
		assertEquals(port, jolokiaSettings.getJolokiaPort());
		assertEquals(path, jolokiaSettings.getJolokiaPath());
	}

}

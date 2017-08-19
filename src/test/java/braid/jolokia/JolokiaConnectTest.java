package braid.jolokia;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import javax.management.MalformedObjectNameException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import braid.beans.jolokia.JolokiaBean;

@RunWith(Arquillian.class)
public class JolokiaConnectTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(J4pClient.class, J4pReadResponse.class, J4pReadRequest.class, J4pClientProducer.class,
						JolokiaConnect.class, JolokiaBean.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	private JolokiaConnect jolokiaConnect;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetJolokiaValues() {
		String value = "";
		try {
			value = jolokiaConnect.getJolokiaValues("jboss.as:deployment=jolokia.war", "status");
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (J4pException e) {
			e.printStackTrace();
		}

		assertEquals("OK", value);
	}

}

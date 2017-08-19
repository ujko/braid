package braid.jolokia;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jolokia.client.J4pClient;

import braid.beans.jolokia.JolokiaBean;

public class J4pClientProducer {

	@Inject
	private JolokiaBean jolokiaBean;

	@Produces
	public J4pClient getJ4PClient() {
		JolokiaSettings jolokiaSettings = jolokiaBean.buildJolokiaSettings();
		String serverAddress = String.format("http://%s:%s/%s", jolokiaSettings.getJolokiaHost(),
				jolokiaSettings.getJolokiaPort(), jolokiaSettings.getJolokiaPath());
		return new J4pClient(serverAddress);
	}

}

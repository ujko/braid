package braid.jolokia;

import java.io.Serializable;

import javax.inject.Inject;
import javax.management.MalformedObjectNameException;

import org.jolokia.client.J4pClient;
import org.jolokia.client.exception.J4pException;
import org.jolokia.client.request.J4pReadRequest;
import org.jolokia.client.request.J4pReadResponse;

public class JolokiaConnect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private J4pClient j4pClient;

	public <T> T getJolokiaValues(String object, String parameter) throws MalformedObjectNameException, J4pException {
		J4pReadResponse resp = j4pClient.execute(new J4pReadRequest(object, parameter));
		return resp.getValue();
	}
}

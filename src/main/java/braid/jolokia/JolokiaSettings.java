package braid.jolokia;

import java.io.Serializable;

public class JolokiaSettings implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String jolokiaHost;
	private final String jolokiaPort;
	private final String jolokiaPath;
	
	public JolokiaSettings(String jolokiaHost, String jolokiaPort, String jolokiaPath) {
		this.jolokiaHost = jolokiaHost;
		this.jolokiaPort = jolokiaPort;
		this.jolokiaPath = jolokiaPath;
	}

	public String getJolokiaHost() {
		return jolokiaHost;
	}

	public String getJolokiaPort() {
		return jolokiaPort;
	}

	public String getJolokiaPath() {
		return jolokiaPath;
	}

}

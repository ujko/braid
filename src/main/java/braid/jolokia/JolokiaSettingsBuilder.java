package braid.jolokia;

public class JolokiaSettingsBuilder {

	private String jolokiaHost;
	private String jolokiaPort;
	private String jolokiaPath;

	protected JolokiaSettingsBuilder() {
	}

	public static JolokiaSettingsBuilder builder() {
		return new JolokiaSettingsBuilder();
	}

	public JolokiaSettingsBuilder withJolokiaHost(final String jolokiaHost) {
		this.jolokiaHost = jolokiaHost;
		return this;
	}

	public JolokiaSettingsBuilder withJolokiaPort(final String jolokiaPort) {
		this.jolokiaPort = jolokiaPort;
		return this;
	}

	public JolokiaSettingsBuilder withJolokiaPath(final String jolokiaPath) {
		this.jolokiaPath = jolokiaPath;
		return this;
	}

	public JolokiaSettingsBuilder from(final JolokiaSettings entity) {
		this.jolokiaHost = entity.getJolokiaHost();
		this.jolokiaPort = entity.getJolokiaPort();
		this.jolokiaPath = entity.getJolokiaPath();
		return this;
	}

	public JolokiaSettingsBuilder but() {
		return builder().withJolokiaHost(jolokiaHost).withJolokiaPort(jolokiaPort).withJolokiaPath(jolokiaPath);
	}

	public JolokiaSettings build() {
		JolokiaSettings jolokiaSettings = new JolokiaSettings(jolokiaHost, jolokiaPort, jolokiaPath);
		return jolokiaSettings;
	}
}

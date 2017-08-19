package braid.beans.jolokia;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import braid.constants.AppConstants;
import braid.jolokia.JolokiaSettings;
import braid.jolokia.JolokiaSettingsBuilder;

@Named(value = "JolokiaBean")
@SessionScoped
public class JolokiaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String jolokiaHost;
	private String jolokiaPort;
	private String jolokiaPath;
	private int refreshTime;

	@PostConstruct
	public void init() {
		jolokiaHost = AppConstants.JOLOKIA_HOST;
		jolokiaPort = AppConstants.JOLOKIA_PORT;
		jolokiaPath = AppConstants.JOLOKIA_PATH;
		refreshTime = AppConstants.REFRESH_TIME;
	}

	public String getJolokiaHost() {
		return jolokiaHost;
	}

	public void setJolokiaHost(String jolokiaHost) {
		this.jolokiaHost = jolokiaHost;
	}

	public String getJolokiaPort() {
		return jolokiaPort;
	}

	public void setJolokiaPort(String jolokiaPort) {
		this.jolokiaPort = jolokiaPort;
	}

	public String getJolokiaPath() {
		return jolokiaPath;
	}

	public void setJolokiaPath(String jolokiaPath) {
		this.jolokiaPath = jolokiaPath;
	}

	public int getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(int refreshTime) {
		this.refreshTime = refreshTime;
	}

	public String connect() {
		return "braid";
	}

	public JolokiaSettings buildJolokiaSettings() {
		JolokiaSettingsBuilder builder = JolokiaSettingsBuilder.builder();
		return builder.withJolokiaHost(jolokiaHost).withJolokiaPort(jolokiaPort).withJolokiaPath(jolokiaPath).build();
	}

}

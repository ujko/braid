package braid.beans.charts;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.management.MalformedObjectNameException;

import org.jolokia.client.exception.J4pException;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import braid.beans.charts.pattern.AbstractChart;
import braid.beans.charts.pattern.ChartOrganize;
import braid.constants.AppConstants;

@Named(value = "PhysicalMemoryChart")
@ViewScoped
public class PhysicalMemoryChart extends AbstractChart implements ChartOrganize{
	private static final long serialVersionUID = 1L;
	private LineChartSeries usedMemorySeries;
	private LineChartSeries maxMemorySeries;
	private int xAxisIterator;

	@Override
	protected void setModelParams() {
		mainChartModel.setTitle("Użycie pamięci fizycznej");
		mainChartModel.getAxis(AxisType.Y).setLabel("MB");
	}

	@Override
	protected void initNewChar() {
		xAxisIterator = 0;
		usedMemorySeries = new LineChartSeries();
		usedMemorySeries.setLabel("Użyta pamięć fizyczna");
		usedMemorySeries.setShowMarker(false);
		maxMemorySeries = new LineChartSeries();
		maxMemorySeries.setLabel("Maksymalna pamięć fizyczna");
		maxMemorySeries.setShowMarker(false);

	}

	@Override
	protected LineChartModel initCategoryModel() {
		if (xAxisIterator > MAX_VALUE_ON_X_AXIS) {
			initNewChar();
		}
		Long freeMemory = getJolokiaValue("java.lang:type=OperatingSystem", "FreePhysicalMemorySize");
		Long totalMemory = getJolokiaValue("java.lang:type=OperatingSystem", "TotalPhysicalMemorySize");
		usedMemorySeries.set(xAxisIterator, freeMemory	/ AppConstants.BYTES_IN_MEGABYTES);
		maxMemorySeries.set(xAxisIterator, totalMemory	/ AppConstants.BYTES_IN_MEGABYTES);
		xAxisIterator++;
		LineChartModel model = new LineChartModel();
		model.addSeries(usedMemorySeries);
		model.addSeries(maxMemorySeries);
		return model;
	}

	private Long getJolokiaValue(String objectName, String paramName) {
		Long jc = 0L;
		try {
			jc = jolokiaConnect.getJolokiaValues(objectName, paramName);
		} catch (MalformedObjectNameException | J4pException e) {
			logger.info("Unable to connect Jolokia");
			errMessage();
		}
		return jc;
	}

}

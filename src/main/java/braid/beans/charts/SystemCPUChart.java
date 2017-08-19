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

@Named(value = "SystemCPUChart")
@ViewScoped
public class SystemCPUChart extends AbstractChart implements ChartOrganize {
	private static final long serialVersionUID = 1L;
	private LineChartSeries cpuUsageSeries;
	private int xAxisIterator;

	@Override
	protected void setModelParams() {
		mainChartModel.setTitle("Użycie procesora");
		mainChartModel.getAxis(AxisType.Y).setLabel("%");
	}

	@Override
	protected void initNewChar() {
		xAxisIterator = 0;
		cpuUsageSeries = new LineChartSeries();
		cpuUsageSeries.setLabel("Użycie procesora %");
		cpuUsageSeries.setShowMarker(false);
	}

	@Override
	protected LineChartModel initCategoryModel() {
		if (xAxisIterator > MAX_VALUE_ON_X_AXIS) {
			initNewChar();
		}
		Double val = getJolokiaValue("java.lang:type=OperatingSystem", "SystemCpuLoad");
		cpuUsageSeries.set(xAxisIterator, val * 100);
		xAxisIterator++;
		LineChartModel model = new LineChartModel();
		model.addSeries(cpuUsageSeries);
		return model;
	}

	private Double getJolokiaValue(String objectName, String paramName) {
		Double jc = 0D;
		
		try {
			jc = jolokiaConnect.getJolokiaValues(objectName, paramName);
		} catch (MalformedObjectNameException | J4pException e) {
			errMessage();
		}
		return jc;
	}

}

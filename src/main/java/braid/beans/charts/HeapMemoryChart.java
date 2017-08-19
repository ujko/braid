package braid.beans.charts;

import java.util.Map;

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

@Named(value = "HeapMemoryChart")
@ViewScoped
public class HeapMemoryChart extends AbstractChart implements ChartOrganize {
	private static final long serialVersionUID = 1L;
	private LineChartSeries lineChartSerie1;
	private LineChartSeries lineChartSerie2;

	private int xAxisIterator;

	@Override
	protected void setModelParams() {
		mainChartModel.setTitle("Stos");
		mainChartModel.getAxis(AxisType.Y).setLabel("MB");
	}

	@Override
	protected void initNewChar() {
		xAxisIterator = 0;
		lineChartSerie1 = new LineChartSeries();
		lineChartSerie1.setLabel("Użyta pamięć");
		lineChartSerie1.setShowMarker(false);
		lineChartSerie2 = new LineChartSeries();
		lineChartSerie2.setLabel("Maksymalna pamięć");
		lineChartSerie2.setShowMarker(false);
	}

	@Override
	protected LineChartModel initCategoryModel() {
		if (xAxisIterator > MAX_VALUE_ON_X_AXIS) {
			initNewChar();
		}
		Long usedMemory = getJolokiaValue("used");
		Long maxMemory = getJolokiaValue("max");
		lineChartSerie1.set(xAxisIterator, usedMemory / AppConstants.BYTES_IN_MEGABYTES);
		lineChartSerie2.set(xAxisIterator, maxMemory / AppConstants.BYTES_IN_MEGABYTES);
		xAxisIterator++;
		LineChartModel model = new LineChartModel();
		model.addSeries(lineChartSerie1);
		model.addSeries(lineChartSerie2);
		return model;
	}

	private Long getJolokiaValue(String param) {
		Map<String, Long> jolokiaValues = null;
		try {
			jolokiaValues = jolokiaConnect.getJolokiaValues("java.lang:type=Memory", "HeapMemoryUsage");
		} catch (MalformedObjectNameException | J4pException e) {
			errMessage();
			return 0L;
		}
		return jolokiaValues.get(param);
	}
}

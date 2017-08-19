package braid.beans.charts.pattern;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;

import braid.jolokia.JolokiaConnect;

public abstract class AbstractChart implements Serializable, ChartOrganize {
	private static final long serialVersionUID = 1L;
	
	protected static final int MAX_VALUE_ON_X_AXIS = 150;

	protected LineChartModel mainChartModel = null;

	@PostConstruct
	public void init() {
		initNewChar();
		createLineModels();
	}

	@Inject
	protected Logger logger;
	
	@Inject
	protected JolokiaConnect jolokiaConnect;

	public LineChartModel getMainChartModel() {
		return mainChartModel;
	}

	public String createLineModels() {
		mainChartModel = initCategoryModel();
		setModelParams();
		mainChartModel.setShowPointLabels(false);
		mainChartModel.setShowDatatip(false);
		mainChartModel.setShadow(false);
		mainChartModel.setStacked(false);
		mainChartModel.setLegendPosition("ne");

		setAxisXSettings();
		setAxisYSettings();
		return "";
	}

	private void setAxisYSettings() {
		Axis axisY = mainChartModel.getAxis(AxisType.Y);
		axisY.setMin(0);
	}

	private void setAxisXSettings() {
		Axis axisX = mainChartModel.getAxis(AxisType.X);
		axisX.setMin(0);
		axisX.setMax(MAX_VALUE_ON_X_AXIS);
		axisX.setTickCount(10);
		axisX.setTickInterval("");
	}

	protected void errMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				"Brak danych - sprawdź ustawienia", "Jolokia error"));
	}
	

	protected abstract void setModelParams();

	protected abstract void initNewChar();

	protected abstract LineChartModel initCategoryModel();

}

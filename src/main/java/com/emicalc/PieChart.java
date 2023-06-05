package com.emicalc;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

@SuppressWarnings("deprecation")
@ManagedBean
public class PieChart {
    private PieChartModel model;

    @PostConstruct
    public void initializeModel() {
        model = new PieChartModel();
        model.set("Brand 1", 540);
        model.set("Brand 2", 325);
        model.set("Brand 3", 702);
        model.set("Brand 4", 421);
        model.setTitle("EMI CHART");
        model.setLegendPosition("w");
    }

    public PieChartModel getModel() {
        return model;
    }
}

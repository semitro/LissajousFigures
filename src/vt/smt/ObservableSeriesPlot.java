package vt.smt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * Created by semitro on 26.10.17.
 */
public class ObservableSeriesPlot extends Plot {
    public ObservableSeriesPlot() {
        super();
        lineChart.getData().clear();
        integrateSeries = new XYChart.Series(FXCollections.observableArrayList());
        lineChart.getData().add(integrateSeries);
        lineChart.setAnimated(false);
    }

    public ObservableList<XYChart.Data> getObservableDots() {
        return integrateSeries.getData();
    }
}

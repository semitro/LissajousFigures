package vt.smt.phys;

import javafx.scene.chart.XYChart;

public interface LissajuInterface {
    // time in milliseconds
    XYChart.Data getValues(SignalInterface signal1, SignalInterface signal2, long time);
}

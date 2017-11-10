package vt.smt.phys;

import javafx.scene.chart.XYChart;

/**
 * Created by semitro on 26.10.17.
 */
public class Lissaju implements LissajuInterface{
    @Override
    public XYChart.Data getValues(SignalInterface signal1, SignalInterface signal2, long time) {
        double x = signal1.getAmplitude()*(Math.sin(signal1.getFrequency() * time + signal1.getPhaze()));
        double y = signal2.getAmplitude()*(Math.sin(signal2.getFrequency() * time + signal2.getPhaze()));
        return new XYChart.Data(x,y);
    }
}

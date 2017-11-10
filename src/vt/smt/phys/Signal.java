package vt.smt.phys;

/**
 * Created by semitro on 26.10.17.
 */
public class Signal implements SignalInterface {
    // Радианы
    private Double frequency = 0.;
    private Double amplitude = 0.;
    private Double phaze = 0.;

    @Override
    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    @Override
    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public void setPhaze(Double phaze) {
        this.phaze = phaze;
    }

    @Override
    public Double getFrequency() {
        return this.frequency;
    }

    @Override
    public Double getAmplitude() {
        return this.amplitude;
    }

    @Override
    public Double getPhaze() {return this.phaze;}
}

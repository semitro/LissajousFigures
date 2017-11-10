package vt.smt.phys;

/**
 * Created by semitro on 26.10.17.
 */
public interface SignalInterface {
    void setFrequency(Double frequency);
    void setAmplitude(Double amplitude);
    void setPhaze(Double phaze);
    Double getFrequency();
    Double getAmplitude();
    Double getPhaze();
}

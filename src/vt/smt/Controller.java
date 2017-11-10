package vt.smt;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import vt.smt.phys.Lissaju;
import vt.smt.phys.LissajuInterface;
import vt.smt.phys.Signal;
import vt.smt.phys.SignalInterface;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.toRadians;

public class Controller implements Initializable{
    @FXML private ObservableSeriesPlot plot;
    private ObservableList<XYChart.Data> dots;

    @FXML private TextField amplitudeField1;
    @FXML private TextField amplitudeField2;

    @FXML private TextField freqField1;
    @FXML private TextField freqField2;

    @FXML private TextField phazeField1;
    @FXML private TextField phazeField2;

    private SignalInterface signal1 = new Signal();
    private SignalInterface signal2 = new Signal();
    private LissajuInterface lissaju = new Lissaju();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        amplitudeField1.setOnKeyPressed(e->{
                try{
                    signal1.setAmplitude(Double.parseDouble(amplitudeField1.getText()));
                }
                catch (Exception ex){}
        });
        amplitudeField2.setOnKeyPressed(e->{
            try{
                signal2.setAmplitude(Double.parseDouble(amplitudeField2.getText()));
            }
            catch (Exception ex){}
        });

        freqField1.setOnKeyPressed(e->{
            try{
                signal1.setFrequency(Double.parseDouble(freqField1.getText()));
            }
            catch (Exception ex){}
        });

        freqField2.setOnKeyPressed(e->{
            try{
                signal2.setFrequency(Double.parseDouble(freqField2.getText()));
            }
            catch (Exception ex){}
        });

        phazeField1.setOnKeyPressed(e->{
            try{
                signal1.setPhaze(toRadians(Double.parseDouble(phazeField1.getText())));
            }
            catch (Exception ex){}
        });
        phazeField2.setOnKeyPressed(e->{
            try{
                signal2.setPhaze( toRadians(Double.parseDouble(phazeField2.getText())) );
            }
            catch (Exception ex){}
        });

        dots = plot.getObservableDots();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        }
        );
        long time = System.currentTimeMillis();

        executor.scheduleAtFixedRate(()->{
            Platform.runLater(()->{
                dots.add(lissaju.getValues(signal1,signal2,(System.currentTimeMillis()-time)));
                if(dots.size() > 600)
                    dots.remove(0);
            });
        },10000,2  , TimeUnit.MILLISECONDS);

    }

    public Controller(){}


}

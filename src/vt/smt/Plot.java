package vt.smt;


import com.sun.istack.internal.Nullable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Plot extends HBox {
    public Plot(){
        super();
        xAxis.setLabel("x");
        yAxis.setLabel("y");
        lineChart = new LineChart(xAxis,yAxis);
        this.getChildren().add(lineChart);

        lineChart.setManaged(true);

    }

    protected XYChart.Series integrateSeries;

    public void setFunction(Function<Number,Number> function, Double from, Double to, @Nullable String name){
        List<XYChart.Data> d1 = new LinkedList<>();
        // Защита от бесконечного цикла
        if(Math.abs(to-from)/256.0  == 0.)
            return;
        // 256 точек
        for(Double i = Math.min(from,to); i < Math.max(to,from); i+= Math.abs(to-from)/256.0) {
            d1.add(new XYChart.Data<Double, Double>(new Double(i), (Double) function.apply(i)));
            // Быстрый костыль для ресайзинга отрезков
            if (maxF < Math.abs((Double) function.apply(i)));
                maxF = Math.abs((Double)function.apply(i));
        }
        d1.add(new XYChart.Data<Double, Double>(new Double(Math.max(from,to)),
                (Double) function.apply(new Double(Math.max(from,to)))));
        ObservableList<XYChart.Data> data = FXCollections.observableList(d1);
        integrateSeries = new XYChart.Series(data);
        if(name == null) integrateSeries.setName("Интегрируемая функция");
        else             integrateSeries.setName(name);
        lineChart.getData().add(integrateSeries);


    }
    private Double maxF = 0.;
    // Добавить отметку
    public XYChart.Series getSeries(){
        return integrateSeries;
    }
    public void addFunction(List<Pair<Double,Double>> table){
        List<XYChart.Data> d1 = new LinkedList<>();
        table.forEach(e->d1.add( new XYChart.Data<>(e.getKey(),e.getValue())));
        ObservableList<XYChart.Data> data = FXCollections.observableList(d1);
        XYChart.Series x = new XYChart.Series(data);

        x.setName(null);
        lineChart.getData().add(x);

    }
    public void setTitle(String title){
        lineChart.setTitle(title);
    }
    public void setTitleUnderPlot(String title){
        integrateSeries.setName(title);
    }
    public void setClueOnMouseEnterPlot(String helpMessage){
        Tooltip tooltip = new Tooltip(helpMessage);
//        Tooltip.install(lineChart.getClip(), tooltip);
        Tooltip.install(integrateSeries.getNode(),tooltip);
    }
    public void clear(){
        lineChart.getData().clear();
        maxF = 0.0;
    }

    protected NumberAxis xAxis = new NumberAxis();
    protected NumberAxis yAxis = new NumberAxis();
    protected XYChart lineChart;
}

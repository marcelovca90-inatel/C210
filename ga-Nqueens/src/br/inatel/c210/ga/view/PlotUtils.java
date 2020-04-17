package br.inatel.c210.ga.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class PlotUtils
{
    private static List<Double> dataX = new ArrayList<>();
    private static List<Double> dataY = new ArrayList<>();

    public static void add(double x, double y)
    {
        dataX.add(x);
        dataY.add(y);
    }

    public static JFrame show()
    {
        JFrame plot = null;

        // set up
        XYChart chart = QuickChart.getChart(
            "Average fitness per generation",
            "Generation",
            "Average fitness",
            "Average fitness per generation",
            dataX.stream().mapToDouble(Double::doubleValue).toArray(),
            dataY.stream().mapToDouble(Double::doubleValue).toArray());

        // display
        plot = new SwingWrapper<>(chart).displayChart();
        plot.setTitle("Execution visualization");

        return plot;
    }
}

package br.inatel.c210.ga.plot;

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
            "Generation vs Number of Attacking Pairs",
            "Generation",
            "Number of Attacking Pairs",
            "Number of Attacking Pairs (Generation)",
            dataX.stream().mapToDouble(Double::doubleValue).toArray(),
            dataY.stream().mapToDouble(Double::doubleValue).toArray());

        // display
        plot = new SwingWrapper<>(chart).displayChart();

        return plot;
    }
}

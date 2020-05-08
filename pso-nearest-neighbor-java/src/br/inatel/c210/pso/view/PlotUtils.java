package br.inatel.c210.pso.view;

import java.util.List;
import java.util.stream.Collectors;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import br.inatel.c210.pso.entity.Particle;

public class PlotUtils
{
    private static XYChart chart;
    private static SwingWrapper<XYChart> wrapper;
    private static final String SERIES_NAME = "particles";

    public static void show(List<Particle> population)
    {
        chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);
        // chart.getStyler().setXAxisMin(-1000.0);
        // chart.getStyler().setXAxisMax(+1000.0);
        // chart.getStyler().setYAxisMin(-1000.0);
        // chart.getStyler().setYAxisMax(+1000.0);

        chart.addSeries(
            SERIES_NAME,
            population.stream().map(p -> p.getPositionAt(0)).collect(Collectors.toList()),
            population.stream().map(p -> p.getPositionAt(1)).collect(Collectors.toList()));

        wrapper = new SwingWrapper<>(chart);
        wrapper.displayChart();
    }

    public static void update(List<Particle> population)
    {
        chart.updateXYSeries(
            SERIES_NAME,
            population.stream().map(p -> p.getPositionAt(0)).collect(Collectors.toList()),
            population.stream().map(p -> p.getPositionAt(1)).collect(Collectors.toList()),
            null);
        wrapper.repaintChart();
    }
}

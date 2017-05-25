package ENSK;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


import java.awt.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Enathen on 2017-04-22.
 */
public class BarGraph extends Graph {
    class CustomRenderer extends BarRenderer {

        /** The colors. */
        private Paint[] colors;

        /**
         * Creates a new renderer.
         *
         * @param colors  the colors.
         */
        public CustomRenderer(final Paint[] colors) {
            this.colors = colors;
        }

        /**
         * Returns the paint for an item.  Overrides the default behaviour inherited from
         * AbstractSeriesRenderer.
         *
         * @param row  the series.
         * @param column  the category.
         *
         * @return The item color.
         */
        public Paint getItemPaint(final int row, final int column) {
            return this.colors[column % this.colors.length];
        }
    }



    public BarGraph(String title, TreeSet linkedListOfArticles, float value,String categoryAxisLabel) {
        super(title, value);
        final CategoryDataset dataSet = createDataSet(linkedListOfArticles);
        final JFreeChart chart = createChart(graphTitle(),dataSet,categoryAxisLabel);

        final ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setMouseWheelEnabled(true);

        setContentPane(chartPanel);

    }


    private static CategoryDataset createDataSet(TreeSet linkedListOfArticles) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        Iterator<Article> iterator = linkedListOfArticles.iterator();


        while (iterator.hasNext()){
            Article article =  iterator.next();
            dataSet.addValue(article.getTotalDifferenceValueArticle(),"S1",article.getArticleName());
        }
        return dataSet;

    }
    private JFreeChart createChart(String title, final CategoryDataset dataSet, String categoryAxisLabel) {

        final JFreeChart chart = ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                "Value",
                dataSet,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        chart.setBackgroundPaint(Color.lightGray);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setNoDataMessage("NO DATA!");

        final CategoryItemRenderer renderer = new CustomRenderer(
                new Paint[] {Color.red, Color.blue, Color.green,
                        Color.yellow, Color.orange, Color.cyan,
                        Color.magenta, Color.blue}
        );

        StandardCategoryToolTipGenerator tt
                = new StandardCategoryToolTipGenerator("{1}: {2} projects",
                new DecimalFormat("0"));
        renderer.setBaseToolTipGenerator(tt);

        plot.setRenderer(renderer);


        // change the margin at the top of the range axis...
        final ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setLowerMargin(0.15);
        rangeAxis.setUpperMargin(0.15);

        return chart;

    }



}

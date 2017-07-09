package ENSK.Windows.Article;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.Iterator;
import java.util.TreeSet;


public class PieGraph extends Graph {


    public PieGraph(String title, TreeSet linkedListOfArticles, float value) {
        super(title, value);

        JFreeChart chart = createChart(getTitle(),createDataSet(linkedListOfArticles));
        new ChartPanel(chart).setMouseWheelEnabled(true);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        setContentPane(chartPanel);

    }



    protected static PieDataset createDataSet(TreeSet linkedListOfArticles) {
        Iterator<Article> iterator = linkedListOfArticles.iterator();
        DefaultPieDataset dataSet = new DefaultPieDataset();
        while (iterator.hasNext()){
            Article article =  iterator.next();
            dataSet.setValue(article.getArticleName(), new Double(article.getTotalDifferenceValueArticle()));
        }
        return dataSet;
    }

    protected static JFreeChart createChart(String s, PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                s, dataset,true,true,false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {1}kr {2}"));
        return chart;
    }

}
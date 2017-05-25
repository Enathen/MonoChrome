package ENSK;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.Iterator;
import java.util.TreeSet;


public class Graph extends ApplicationFrame {
    float value;
    public Graph(String title, float value) {

        super(title);

        this.value = value;


    }
    protected String graphTitle(){
        String s = Float.toString(value);
        return s.concat("Kr totalt");

    }





}
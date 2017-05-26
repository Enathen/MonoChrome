package ENSK.Windows;

import org.apache.batik.swing.JSVGCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Enathen on 2017-04-22.
 */
public class WindowSuperMarket extends JFrame{
    private JPanel panelInsertArticle;

    private JTextField importPriceTextField;
    private JTextField sellPriceTextFIeld;
    private JTextField onSalePriceTextField;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTextField barcodeTextField;
    private JTextField stockOfArticleTextField;
    private JTextField weightOfArticleTextField;
    private JTextField articleNameTextField;

    private JLabel sellPriceLabel;
    private JLabel importPriceLabel;
    private JLabel expiringDateLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel barcodeLabel;
    private JLabel stockOfArticleLabel;
    private JLabel weightOfArticleLabel;
    private JLabel onSalePriceLabel;
    private JLabel stockOfArticle;
    private JLabel insertArticleNameLabel;

    private JButton addArticleButton;
    private JButton SaveArticles;
    private JScrollPane articleScrollPanel;
    private JPanel testPanel;


    public WindowSuperMarket(String title){
        super(title);
        panelInsertArticle.setMinimumSize(new Dimension(540,280));
        setContentPane(panelInsertArticle);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSVGCanvas svg = new JSVGCanvas();
        svg.setURI("file:/C:/Users/Enathen/IdeaProjects/ENSK/button.svg");
        //svg.setMaximumSize(new Dimension(600,250));
        //testPanel.add(svg);



        setVisible(true);
        addArticleButton.addActionListener(e -> {
            boolean noError = true;
            if(restrictToFloat(importPriceTextField, importPriceLabel)){
                noError = false;
            }
            if(restrictToFloat(sellPriceTextFIeld, sellPriceLabel)){
                noError = false;
            }
            if(restrictToFloat(onSalePriceTextField, onSalePriceLabel)){
                noError = false;
            }
            if(restrictToFloat(weightOfArticleTextField, weightOfArticleLabel)){
                noError = false;
            }
            if(restrictToFloat(stockOfArticleTextField, stockOfArticleLabel)){
                noError = false;
            }
            if(restrictToInt(dayTextField, expiringDateLabel)){
                noError = false;
            }
            if(restrictToInt(monthTextField, expiringDateLabel)){
                noError = false;
            }
            if(restrictToIntSpecialCase(yearTextField, expiringDateLabel)){
                noError = false;
            }
            if(restrictToInt(barcodeTextField, barcodeLabel)){
                noError = false;
            }
            if(checkIfTextFieldIsEmpty(articleNameTextField)){
                noError = false;
            }
            if(checkIfTextFieldIsEmpty(importPriceTextField)){
                noError = false;
            }
            if(checkIfTextFieldIsEmpty(sellPriceTextFIeld)){
                noError = false;
            }
            if(noError){

            }

        });
        SaveArticles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    private boolean restrictToFloat(JTextField textField, JLabel label){
        String string = label.getText();
        try {
            if(string.endsWith(" Need to be numerical")) {
                string = string.replace(" Need to be numerical","");
                label.setText(string);
            }
            Float.parseFloat(textField.getText());
        } catch(Exception e) {
            string = label.getText();
            if(!string.endsWith("numerical")){
                label.setText(string.concat(" Need to be numerical"));
            }
            return false;
        }
        return true;
    }
    private boolean restrictToInt(JTextField textField, JLabel label){
        String string = label.getText();
        try {

            Integer.parseInt(textField.getText());
            if(string.endsWith(" Need to be numerical")) {
                string = string.replace(" Need to be numerical","");
                label.setText(string);
            }
        } catch(Exception e) {
            string = label.getText();
            if(!string.endsWith(" Need to be numerical")) {
                label.setText(string.concat(" Need to be numerical"));
            }
            return false;
        }
        return true;
    }
    private boolean restrictToIntSpecialCase(JTextField textField, JLabel label){
        try {
            Integer.parseInt(textField.getText());
        } catch(Exception e) {
            String string = label.getText();
            if(!string.endsWith(" Need to be numerical")) {
                label.setText(string.concat(" Need to be numerical"));
            }
            return false;
        }
        return true;
    }
    private boolean checkIfTextFieldIsEmpty(JTextField textField){
        String string = textField.getText();
        if(string.isEmpty() || string.endsWith("Cannot be empty")){
            textField.setText("Cannot be empty");
            return false;
        }
        return true;
    }


}

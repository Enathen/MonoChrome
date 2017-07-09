package ENSK.Windows.Article;

import ENSK.Windows.Connection.ConnectionArticles;
import ENSK.Windows.Design.SetDesignCorrectly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Enathen on 2017-06-14.
 */
public class AddArticleFrame extends JFrame {
    private JPanel JPanelAddArticle;
    private JLabel qrButton;
    private JTextField barcodeTextField;
    private JLabel barcodeExistSymbolLabel;
    private JLabel barcodeExistLabel;
    private JTextField exampleNameTextField;
    private JTextField amountOfArticlesTextField;
    private JTextField weightTextField;
    private JTextField dayTextField;
    private JTextField inPriceTextfield;
    private JComboBox categoryComboBox;
    private JPanel JPanelSelectedArticles;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTextField sellPriceTextField;
    private JTextField onSalePriceTextField;
    private JLabel addArticleLabel;
    private JLabel addAllArticleLabel;
    private JLabel removeSelectedArticlesLabel;
    private JLabel stapleLeft;
    private JLabel stapleRight;
    private JLabel lowerLeft;
    private JLabel upperLeft;
    private JLabel upperRight;
    private JLabel lowerRight;
    private JCheckBox articleCheckBox;
    private JLabel articleNamePanelLabel;
    private JLabel categoryPanelLabel;
    private JLabel barcodePanelLabel;
    private JLabel amountPanelLabel;
    private JLabel wieghtPanelLabel;
    private JLabel expiryDatePanelLabel;
    private JLabel inPricePanelLabel;
    private JLabel sellPricePanelLabel;
    private JLabel onSalePricePanelLabel;
    private JLabel backLabel;
    private JLabel noConnectionLabel;
    private JTextField wasteTextField;
    private JLabel wasteLabel;
    private JLabel addCategoryLabel;

    public AddArticleFrame(){
        initialize();
        barcodeExistLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();

                if(barcodeExist()){
                    setDesignCorrectly.setDisapproveSymbol(barcodeExistSymbolLabel, 1);
                }
                else {
                    setDesignCorrectly.setApproveSymbol(barcodeExistSymbolLabel, 1);
                }

            }
        });
    }
    private void initialize(){
        JPanelAddArticle.setMinimumSize(new Dimension(540,280));
        JPanelAddArticle.setBackground(Color.white);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignBlue(qrButton, 1);
        setDesignCorrectly.setDesignOrange(barcodeExistLabel, 1);
        setDesignCorrectly.setDesignLime(addArticleLabel, 1);
        setDesignCorrectly.setDesignLime(addAllArticleLabel, 1);
        setDesignCorrectly.setDesignBlue(removeSelectedArticlesLabel, 1);
        setDesignCorrectly.setDesignBlue(backLabel, 0.4);
        setDesignCorrectly.setDesignBlue(addCategoryLabel,0.4);

        setDesignCorrectly.setButtonCorrectly("/res/upperLeft.png", upperLeft);
        setDesignCorrectly.setButtonCorrectly("/res/lowerLeft.png", lowerLeft);
        setDesignCorrectly.setButtonCorrectly("/res/upperRight.png", upperRight);
        setDesignCorrectly.setButtonCorrectly("/res/lowerRight.png", lowerRight);
        setDesignCorrectly.setButtonCorrectly("/res/stapleRight.png", stapleRight);
        wasteLabel.setVisible(false);
        wasteTextField.setVisible(false);
        JPanelSelectedArticles.setBackground(Color.gray);
        setContentPane(JPanelAddArticle);
        categoryComboBox.addItem("Bygg");
        categoryComboBox.addItem("Chark");
        categoryComboBox.addItem("Deli");
        categoryComboBox.addItem("Mejeri");
        categoryComboBox.addItem("Non-Food");
        categoryComboBox.addItem("Kolonial");
        categoryComboBox.addItem("Restaurang");
        categoryComboBox.addItem("Frys");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setResizable(false);
    }
    private boolean barcodeExist(){
        ConnectionArticles connectionArticles = new ConnectionArticles(noConnectionLabel);
        connectionArticles.barcodeExist(barcodeTextField.getText());
        return true;
    }
}

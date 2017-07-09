package ENSK.Windows.Change;

import ENSK.Windows.Design.SetDesignCorrectly;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-07-06.
 */
public class ChangeWorkplace extends JFrame{
    private JLabel workplaceIncorrectLabel;
    private JLabel passwordIncorrectLabel;
    private JLabel noConnectionLabel;
    private JComboBox workPlaceComboBox;
    private JLabel changeWorkplaceLabel;
    private JPasswordField passwordField;
    private JPanel JPanelWorkPlace;

    public ChangeWorkplace(){
        initialize();
    }

    private void initialize() {
        JPanelWorkPlace.setMinimumSize(new Dimension(540,280));
        JPanelWorkPlace.setBackground(Color.white);
        setContentPane(JPanelWorkPlace);
        SetDesignCorrectly setDesignCorrectly = new SetDesignCorrectly();
        setDesignCorrectly.setDesignOrange(changeWorkplaceLabel, 0.8);
        noConnectionLabel.setVisible(false);
        pack();
        setResizable(false);
    }
}

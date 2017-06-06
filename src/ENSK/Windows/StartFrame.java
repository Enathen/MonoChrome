package ENSK.Windows;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Enathen on 2017-06-06.
 */
public class StartFrame extends JFrame{
    private JPanel JPanelStartFrame;

    public StartFrame(){
        initialize();
    }
    private void initialize(){
        JPanelStartFrame.setMinimumSize(new Dimension(540,280));

        setContentPane(JPanelStartFrame);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}

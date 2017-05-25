import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by Enathen on 2017-03-27.
 */
public class Window {
    private JFrame window = new JFrame("BARCODE");
    public void Window(){
        setWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        JButton button = new JButton("SWAG");
        button.setMnemonic(KeyEvent.VK_D);
        button.setBounds(10,20,100,100);
        button.setVisible(true);
        button.setFont(button.getFont().deriveFont(20f));
        window.add(button);

    }
    public void setWindow() {
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true); //takes away top.

    }

}

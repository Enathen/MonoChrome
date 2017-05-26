package ENSK.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Enathen on 2017-05-26.
 */
public class LoginFrame extends JFrame{
    private JPasswordField passwordPasswordField;
    private JFormattedTextField usernameFormattedTextField;
    private JButton loginButton;
    private JButton forgotPasswordButton;
    private JButton menuButton;
    private JButton createNewAccountButton;
    private JPanel JPanelLoginFrame;

    private JLabel welcomeLabel;
    private JLabel userPasswordWrong;


    public LoginFrame() {
        initialize();

        /**
         * when pressing login button check if password is correct and user exist in account database.
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(passwordPasswordField.getPassword());

                if(password.equals("hej")&& usernameFormattedTextField.getText().equals("hej")){
                    new WindowSuperMarket("HELLO").setVisible(true);
                }

                userPasswordWrong.setText("<html>Username and/or <br>password are wrong!</html>");

                
            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNewAccount().setVisible(true);
            }
        });
    }
    private void initialize(){
        JPanelLoginFrame.setMinimumSize(new Dimension(540,280));

        setContentPane(JPanelLoginFrame);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        welcomeLabel.setFont(new Font("default", Font.PLAIN, 20));
        welcomeLabel.setText("<html>Welcome!<br>Please sign in below:</html>");
    }
}

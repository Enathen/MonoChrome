package ENSK.Windows.Account;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Password {
    private String password;
    private String salt;
    public Password(String password){
        this.password = password;
    }

    public Password() {

    }

    public String createSalt() {
        SecureRandom random = new SecureRandom();
        String string = "";
        for (int i = 0; i < 50; i++) {
            string += (char)(random.nextInt(58) + 65);
        }
        salt = string;
        return string;
    }
    public String createHash(){
        String hash = salt + password;
        return String.valueOf(hash(hash));

    }
    private static long hash(String string) {
        long h = 288230376151711717L; // prime
        int len = string.length();

        for (int i = 0; i < len; i++) {
            h = 37*h + string.charAt(i);
        }
        return h;
    }
    public void createRandomPassword(){
        SecureRandom random = new SecureRandom();
        String string = "";
        for (int i = 0; i < 8; i++) {
            string += (char)(random.nextInt(58) + 65);
        }
        password = string;
    }
    public void setSalt(String salt){
        this.salt = salt;
    }
    public String getPassword(){
        return password;
    }
    public boolean passwordCorectFormat(JPasswordField passwordField, JPasswordField passwordField1,
                                        JLabel passwordIncorrectLabel ){
        passwordIncorrectLabel.setVisible(false);
        String password = String.valueOf(passwordField.getPassword());
        String password2 = String.valueOf(passwordField1.getPassword());
        if(!password.equals(password2)){

            passwordIncorrectLabel.setText("<html>Password incorrect!<br>not equals!</html>");
            passwordIncorrectLabel.setVisible(true);
            return false;
        }
        if(!(passwordField.getPassword().length > 7)){
            passwordIncorrectLabel.setText("<html>Password incorrect!<br>to short!</html>");
            passwordIncorrectLabel.setVisible(true);
            return false;

        }
        if(!(passwordField.getPassword().length < 64)){
            passwordIncorrectLabel.setText("<html>Password incorrect!<br>to long!</html>");
            passwordIncorrectLabel.setVisible(true);
            return false;

        }
        for(char character : passwordField.getPassword()){

            if(!((character >= 'A' && character <= 'z') || (character >= '1' && character <= '9'))){
                passwordIncorrectLabel.setText("<html>Password incorrect!<br>A-z & 1-9</html>");
                passwordIncorrectLabel.setVisible(true);
                return false;

            }
        }
        return true;
    }
}

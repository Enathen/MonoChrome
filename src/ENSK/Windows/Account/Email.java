package ENSK.Windows.Account;

import ENSK.Windows.Connection.ConnectionAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Email {
    String email;


    public Email(String email) {

        this.email = email.toLowerCase();
    }


    public boolean checkIfEmail() {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound) {

            return true;

        }
        return false;
    }

    public boolean checkIfEmailExists(ConnectionAccount connection) {

        String sql = "select email from Account where email = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public String getStringEmail() {
        return email;
    }
    // Set up the SMTP server.

    public boolean sendEmail(String to, ConnectionAccount connection)  {
        final String username = "creativeendlessgrowing@gmail.com";
        final String password = "TossHealthFeastUnderstand99";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // Construct the message
        String subject = "Hello new Password!";

        try {
            System.out.println(to);
            System.out.println(username);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            msg.setSubject(subject);
            Password saltAndHashPassword = new Password("");
            saltAndHashPassword.createRandomPassword();
            msg.setText(" your new password is: " + saltAndHashPassword.getPassword());

            // Send the message.

            Transport.send(msg);
            System.out.println("email sent successfully");
            PreparedStatement update = connection.prepareStatement
                    ("UPDATE Account SET saltedHash = ?, hash = ? WHERE email = ?");
            update.setString(1,saltAndHashPassword.createSalt());
            update.setString(2,saltAndHashPassword.createHash());
            update.setString(3, email);
            update.executeUpdate();

            return true;
        } catch (MessagingException e) {
            throw new RuntimeException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean EmailCorrectFormat(JLabel emailIncorrectLabel){
        emailIncorrectLabel.setVisible(false);
        if(!(checkIfEmail())){
            emailIncorrectLabel.setText("<html>Email incorrect!<br>" +
                    "Format not x@x.x</html>");
            emailIncorrectLabel.setVisible(true);
            return false;

        }
        if(!(email.length() < 64)){
            emailIncorrectLabel.setText("<html>Email incorrect!<br>" +
                    "max 64 letters</html>");
            emailIncorrectLabel.setVisible(true);
            return false;
        }
        return true;
    }


}

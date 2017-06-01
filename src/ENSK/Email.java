package ENSK;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Email {
    String email;
    ConnectionClass connection = new ConnectionClass();


    public Email(String email) throws SQLException, ClassNotFoundException {

        this.email = email.toLowerCase();
        checkIfEmail();
        checkIfEmailExists();
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

    public boolean checkIfEmailExists() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connection.getConnection();
        }

        String sql = "select email from Account where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return true;
        }
        return false;
    }

    public String getStringEmail() {
        return email;
    }
    // Set up the SMTP server.

    public boolean sendEmail() {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", "smtp.myisp.com");
        Session session = Session.getDefaultInstance(props, null);

        // Construct the message
        String to = email;
        String from = "jontekaminen@hotmail.com";
        String subject = "Hello";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText("Hi,\n\nHow are you?");

            // Send the message.
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            // Error.
        }
        return false;
    }

}

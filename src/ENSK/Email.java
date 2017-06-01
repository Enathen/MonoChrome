package ENSK;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Enathen on 2017-05-31.
 */
public class Email {
    String email;
    ConnectionClass connection = new ConnectionClass();


    public Email(String email) throws SQLException, ClassNotFoundException, NamingException {

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
            try {
                connection.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            }
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
        final String username = "jonathanalexnorberg@gmail.com";
        final String password = "Hccolh12";
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
        String to = "elinhelgesson96@hotmail.com";
        String from = "jonathanalexnorberg@gmail.com";
        String subject = "Hello Darling!";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText("Dear miss Elin Helgesson! i finally succeeded to send an email!\n" +
                    "please write back to my messanger when you see this your cute little cutie<3");

            // Send the message.

            Transport.send(msg);
            System.out.println("email sent succesfully");
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException();
        }
    }

}

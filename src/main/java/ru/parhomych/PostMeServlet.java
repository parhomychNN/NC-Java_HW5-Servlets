package ru.parhomych;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostMeServlet extends HttpServlet {

    Logger logger = Logger.getLogger("PostMe");
    private final String emailFrom = "ncnnexampleparhomenko@mail.ru";//
    private final String password = "qwer1Qwert";
    private final String smtpOutgoingHost = "smtp.mail.ru";
    private final String smtpOutgoingHostPort = "465";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Map<String,String[]> parameters = request.getParameterMap();
        String emailTo = parameters.get("email")[0];
        String subject = parameters.get("subject")[0];
        String cc = parameters.get("cc")[0];
        String text = parameters.get("text")[0];

        logger.log(Level.INFO, "emailTo: " + emailTo);
        logger.log(Level.INFO, "emailFrom: " + emailFrom);
        logger.log(Level.INFO, "password: " + password);
        logger.log(Level.INFO, "subject: " + subject);
        logger.log(Level.INFO, "cc: " + cc);
        logger.log(Level.INFO, "text: " + text);

        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpOutgoingHost);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.port", smtpOutgoingHostPort);
        properties.put("mail.smtp.user", emailFrom);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.ssl.trust", smtpOutgoingHost);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", smtpOutgoingHostPort);
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailFrom, password);
                    }
                });

        try {
            MimeMessage message= new MimeMessage(session);
            message.setFrom(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            message.setText(text);
            Transport transport = session.getTransport("smtp");
            transport.connect(null, Integer.parseInt(smtpOutgoingHostPort), emailFrom, password);
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            logger.log(Level.INFO, "Sent!!!");

            response.sendRedirect("success-posting.html");

        } catch (AddressException e) {
            response.sendRedirect("postme.html");
            e.printStackTrace();
        } catch (MessagingException e) {
            response.sendRedirect("postme.html");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

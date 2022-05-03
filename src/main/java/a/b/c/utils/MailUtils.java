package a.b.c.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtils {
    private final JavaMailSenderImpl mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailUtils() throws MessagingException {
        this.mailSender = new JavaMailSenderImpl();
        setMailSender(getProperties());
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    public void setText(String htmlContent) throws MessagingException {
        messageHelper.setText(htmlContent, true);
    }

    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
        messageHelper.setFrom(email, name);
    }

    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    public void addInline(String contentId, javax.activation.DataSource dataSource) throws MessagingException {
        messageHelper.addInline(contentId, dataSource);
    }

    public void send() {
        mailSender.send(message);
    }

    private void setMailSender(Properties properties) {
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("dbflarla496695@gmail.com");
        mailSender.setPassword("DBdbflarla?!");
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return properties;
    }
}

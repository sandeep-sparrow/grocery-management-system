package net.sandeep.grocery.store.service.impl;

import jakarta.mail.internet.MimeMessage;
import net.sandeep.grocery.store.service.EmailService;
import net.sandeep.grocery.store.utility.EmailConstants;
import net.sandeep.grocery.store.utility.EmailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author Sandeep R P
 * @version 1.0
 * @license sandeep-sparrow, GITHUB
 * @since 03/02/2024
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${spring.mail.verify.host}")
    private String host;

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendSimpleMail(String username, String to) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setCc(EmailConstants.CC);
            message.setSubject(EmailConstants.REGISTRATION_EMAIL_SUBJECT);
            message.setText(EmailUtils.getEmailMessage(
                    username, EmailConstants.REGISTRATION_EMAIL_BODY,
                    host, "12345"));

            javaMailSender.send(message);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Async
    public void sendSimpleMailWithAttachments(String username, String to) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(EmailConstants.REGISTRATION_EMAIL_SUBJECT);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setCc(EmailConstants.CC);
            message.setText(EmailUtils.getEmailMessage(
                    username, EmailConstants.REGISTRATION_EMAIL_BODY,
                    host, "12345"));

            javaMailSender.send(message);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    @Async
    public void sendHtmlMail(String username, String to) {

    }

    @Override
    @Async
    public void sendHtmlMailWithEmbeddedFiles(String username, String to) {

    }
}

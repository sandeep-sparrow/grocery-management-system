package net.sandeep.grocery.store.service.impl;

import jakarta.mail.internet.MimeMessage;
import net.sandeep.grocery.store.service.EmailService;
import net.sandeep.grocery.store.utility.EmailConstants;
import net.sandeep.grocery.store.utility.EmailUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;


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
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, EmailConstants.UTF_8);
            helper.setPriority(1);
            helper.setSubject(EmailConstants.REGISTRATION_EMAIL_SUBJECT);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setCc(EmailConstants.CC);
            helper.setText(EmailUtils.getEmailMessage(
                    username, EmailConstants.REGISTRATION_EMAIL_BODY,
                    host, "12345"));
            // Add Attachment
            FileSystemResource myPic =
                    new FileSystemResource(
                            new File(System.getProperty("user.home") +
                                    "/Downloads/images/my-pic.jpg"));
            FileSystemResource dhairya =
                    new FileSystemResource(
                            new File(System.getProperty("user.home") +
                                    "/Downloads/images/dhairya_born_day.docx"));
            FileSystemResource cover =
                    new FileSystemResource(
                            new File(System.getProperty("user.home") +
                                    "/Downloads/images/linked-cover.png"));

            helper.addAttachment(Objects.requireNonNull(myPic.getFilename()), myPic);
            helper.addAttachment(Objects.requireNonNull(dhairya.getFilename()), dhairya);
            helper.addAttachment(Objects.requireNonNull(cover.getFilename()), cover);

            javaMailSender.send(message);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return javaMailSender.createMimeMessage();
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

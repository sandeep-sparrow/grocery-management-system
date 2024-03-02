package net.sandeep.grocery.store.service;

/**
 * @author Sandeep R P
 * @version 1.0
 * @license sandeep-sparrow, GITHUB
 * @since 01/01/0001 (MM/DD/YYYY)
 */
public interface EmailService {

    void sendSimpleMail(String username, String to);
    void sendSimpleMailWithAttachments(String username, String to);
    void sendHtmlMail(String username, String to);
    void sendHtmlMailWithEmbeddedFiles(String username, String to);
}

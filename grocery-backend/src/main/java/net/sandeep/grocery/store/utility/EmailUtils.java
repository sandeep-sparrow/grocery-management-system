package net.sandeep.grocery.store.utility;

/**
 * @author Sandeep R P
 * @version 1.0
 * @license sandeep-sparrow, GITHUB
 * @since 03/02/2024 (MM/DD/YYYY)
 */
public class EmailUtils {

    public static String getEmailMessage(String username, String body, String host, String token){
        return "Hello " + username + ",\n\nYour new account has been created. " +
                "Please click the link below to verify your account. \n\n" +
                getVerificationUrl(host, token) + "\n\n" + body +
                "\n\nThe Support Team";
    }

    private static String getVerificationUrl(String host, String token){
        return host + "api/users?token=" + token;
    }
}

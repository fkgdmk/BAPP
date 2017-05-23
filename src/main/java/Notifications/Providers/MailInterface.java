package Notifications.Providers;

import java.io.IOException;

/**
 * Created by jasonkelly on 23/05/2017.
 */
public interface MailInterface {


    /**
     * Send email
     * @return
     */
    public boolean SendEmail(String fromEmail, String toEmail, String subjectEmail, String bodyEmail) throws IOException;

}

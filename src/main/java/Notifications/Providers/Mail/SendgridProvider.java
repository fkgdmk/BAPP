package Notifications.Providers.Mail;

import Notifications.Providers.MailInterface;
import com.sendgrid.*;

import java.io.IOException;

public class SendgridProvider implements MailInterface {


    private String apiKey;

    public SendgridProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    public boolean SendEmail (String fromEmail, String toEmail, String subjectEmail, String bodyEmail) throws IOException {

           Email from = new Email(fromEmail);
           String subject = subjectEmail;
           Email to = new Email(toEmail);
           Content content = new Content("text/plain", bodyEmail);
           Mail mail = new Mail(from, subject, to, content);

           SendGrid sg = new SendGrid(this.apiKey);
           Request request = new Request();
           try {
               request.setMethod(Method.POST);
               request.setEndpoint("mail/send");
               request.setBody(mail.build());
               Response response = sg.api(request);
               System.out.println(response.getStatusCode());
               System.out.println(response.getBody());
               System.out.println(response.getHeaders());
           } catch (IOException ex) {
               throw ex;
           }

           return true;

    }

}

package Notifications.Providers.SMS;
import Notifications.Providers.SMSInterface;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * Created by Fredrik Mikkelsen on 24-05-2017.
 */

public class NexmoProvider implements SMSInterface
{

    public NexmoProvider () {}

    public boolean sendSMS(String fromPhoneNumber, String toPhoneNumber, String text) throws Exception {

        AuthMethod auth = new TokenAuthMethod("28721246", "c0222d31f439b355");
        NexmoClient client = new NexmoClient(auth);

        TextMessage message = new TextMessage("30703294", toPhoneNumber, text);

            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);

            for(SmsSubmissionResult response : responses)
            {
                System.out.println(response);
            }
            return true;
        }


}



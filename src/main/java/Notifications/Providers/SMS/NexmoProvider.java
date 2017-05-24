package Notifications.Providers.SMS;
import Notifications.Providers.SMSInterface;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

/**
 * Created by Fredrik on 24-05-2017.
 */
public class NexmoProvider implements SMSInterface
{
    private String nexmoApiKey;

    public NexmoProvider(String nexmoApiKey) {
        this.nexmoApiKey = nexmoApiKey;
    }

    public NexmoProvider () {}


    public boolean sendSMS(String fromPhoneNumber, String toPhoneNumber, String text) throws Exception {

        AuthMethod auth = new TokenAuthMethod("28701646", "c0228d31f419b355");
        NexmoClient client = new NexmoClient(auth);

        TextMessage message = new TextMessage(fromPhoneNumber, toPhoneNumber, text);

            SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);

            for(SmsSubmissionResult response : responses)
            {
                System.out.println(response);
            }
            return true;
        }

}



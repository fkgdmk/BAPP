package Notifications.Providers;

/**
 * Created by Fredrik on 24-05-2017.
 */
public interface SMSInterface
{

    /**
     *
     * @param fromPhoneNumber
     * @param toPhoneNumber
     * @param text
     * @return
     * @throws Exception
     */

    public boolean sendSMS (String fromPhoneNumber, String toPhoneNumber, String text) throws Exception;
}

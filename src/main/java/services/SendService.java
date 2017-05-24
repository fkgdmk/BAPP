package services;

import Notifications.Notification;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import models.ContactPerson;
import models.Member;
import org.javalite.activejdbc.Base;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jakob on 24-05-2017.
 */
public class SendService extends Thread
{
    private boolean sendEmail;
    private boolean sendText;
    private int groupID;
    private String subject;
    private String message;

    private boolean runThread;

    public SendService (int _groupID, boolean _sendEmail, boolean _sendText, JFXTextField _subject, JFXTextArea _message)
    {
        groupID = _groupID;
        sendEmail = _sendEmail;
        sendText = _sendText;

        subject = _subject.getText();
        message = _message.getText();
        runThread = true;
    }

    public void run()
    {
        Base.open();
        while(runThread)
        {
            sendEmailsToGroup();
        }
    }

    public void sendEmailsToGroup()
    {
        List<Member> membersInGroup = Member.where("group_id =?", groupID);

        //Find all the members in a group
        for (int i = 0; i < membersInGroup.size(); i++)
        {
            Member member = membersInGroup.get(i);

            //Get their corresponding emails.
            ContactPerson contactPerson = ContactPerson.findFirst("id =?", member.get("contact_person_id"));
            String recipient = contactPerson.get("email").toString();
            String recipientSMS = contactPerson.get("phone").toString();

            //Send email
            Notification notification = new Notification(
                    "test@example.com",
                    recipient,
                    subject,
                    message
            );

            if(sendEmail){
                try {
                    notification.sendMail();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("før sms test");
            if (sendText)
            {
                System.out.println("sms test");
                System.out.println("test");

                Notification notifi = new Notification(
                        "+4530703294",
                        recipientSMS,
                        subject,
                        message
                );

                notifi.sendSMS();
            }
        }

        runThread = false;
    }
}

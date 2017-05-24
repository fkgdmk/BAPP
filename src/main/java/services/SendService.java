package services;

import Notifications.Notification;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import models.ContactPerson;
import models.Member;
import org.javalite.activejdbc.Base;

import java.io.IOException;
import java.util.ArrayList;
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
    private List<Integer> groupIDs = new ArrayList<>();

    private boolean runThread;

    public SendService (int _groupID, boolean _sendEmail, boolean _sendText,
                        JFXTextField _subject, JFXTextArea _message, List<Integer> _groupIDs)
    {
        groupIDs = _groupIDs;
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

    private void sendEmailsToGroup()
    {
        int amountOfGroups = groupIDs.size();
        List<Member> membersInGroup = Member.where("group_id =?", groupID);

        for (int x = 0; x < amountOfGroups; x++)
        {
            //Collect members in group(s).
            membersInGroup.addAll(Member.where("group_id =?", groupIDs.get(x)));

            //Get their corresponding emails.
            ContactPerson contactPerson = ContactPerson.findFirst("id =?",
                    membersInGroup.get(x).get("contact_person_id"));
            String recipientEmail = contactPerson.get("email").toString();
            String recipientSMS = contactPerson.get("phone").toString();

            //Create notification to send
            Notification notification = new Notification(
                    "test@example.com",
                    recipientEmail,
                    subject,
                    message
            );

            //Send email if requested
            if(sendEmail){
                try {
                    notification.sendMail();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Send text if requested
            if (sendText)
            {
                Notification notifi = new Notification(
                        "+4530703294",
                        recipientSMS,
                        subject,
                        message
                );

                notifi.sendSMS();
            }
        }

        /*
        //Find all the members in a group
        for (int i = 0; i < membersInGroup.size(); i++)
        {
            Member member = membersInGroup.get(i);

            //Get their corresponding emails.
            ContactPerson contactPerson = ContactPerson.findFirst("id =?", member.get("contact_person_id"));
            String recipient = contactPerson.get("email").toString();

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

            if (sendText)
            {
                System.out.println("Send text too (SendService)");
            }
        }*/

        runThread = false;
    }
}

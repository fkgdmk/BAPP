package services;

import Notifications.Notification;
import Notifications.Providers.SMS.NexmoProvider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import models.*;
import seeders.MessageTableSeeder;
import models.Message;
import models.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jakob on 20-05-2017.
 */
public class MessageService
{
    public JFXTextArea textArea; //message
    public JFXTextField textField; //subject
    public Label dateLabel;
    public Label errorLabel;
    public VBox container;
    public boolean email_CheckBox;
    public boolean text_CheckBox;
    public boolean facebook_CheckBox;

    public void setTextArea(JFXTextArea _textArea)
    {
        textArea = _textArea;
    }

    public void setErrorLabel(Label _errorLabel)
    {
        errorLabel = _errorLabel;
    }

    public void setTextField(JFXTextField _textField)
    {
        textField = _textField;
    }

    public void setDateLabel(Label _dateLabel)
    {
        dateLabel = _dateLabel;
    }

    public void setSentMessagesContainer(VBox _container)
    {
        container = _container;
    }

    public void saveMessage(boolean email_CheckBox, boolean text_CheckBox, boolean facebook_CheckBox,
                            boolean sendToGroup1, boolean sendToGroup2, boolean sendToGroup3)
    {

        Notification notification = new Notification(
                "test@example.com",
                "frederik.lippert@gmail.com",
                textField.getText(),
                textArea.getText()
        );
            if(email_CheckBox){
            try {
                notification.sendMail();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }


        MessageTableSeeder messageTableSeeder = new MessageTableSeeder();
        messageTableSeeder.Seed(textArea.getText(), textField.getText(),
                email_CheckBox, text_CheckBox, facebook_CheckBox, sendToGroup1, sendToGroup2, sendToGroup3);
    }

    public void showSentMessages()
    {
        //Get messages in DB and store them in a list
        List<Message> list = Message.findAll();

        //Count message entries in VBox
        int amountOfChildren = container.getChildren().size();
        System.out.println("AOC: " + amountOfChildren);
        //Count messages in database
        int amountOfMessages = list.size();
        System.out.println("AOM: " + amountOfMessages);
        //Calculate how many many message entries need to be generated
        int difference = amountOfMessages - amountOfChildren;

        //If we have more messages in the database, than there are
        //entries (HBoxes) in the container (VBox), we have to create new ones.
        if (difference > 0)
        {
            System.out.println("There is a difference of " + difference + ". Creating new entries...");
            //Create message entries as needed
            for (int i = 0; i < difference; i++)
            {
                HBox newHBox = new HBox(40);
                newHBox.setPadding(new Insets(0, 0, 15, 0));
                newHBox.setPrefHeight(20);

                Label sub = new Label();
                sub.setPrefWidth(120);
                Label rec = new Label();
                rec.setPrefWidth(120);
                Label sen = new Label();
                sen.setPrefWidth(120);
                Label dat = new Label();
                dat.setPrefWidth(120);
                newHBox.getChildren().addAll(sub, rec, sen, dat);

                container.getChildren().add(amountOfMessages-(difference), newHBox);
            }
        }

        //Recalculate amount of message entries (because we just generated new ones)
        amountOfChildren = container.getChildren().size();

        //Loop through all VBox-entries
        for (int i = 0; i < amountOfChildren; i++)
        {
            //Grab an entry (child @ i)
            HBox messageInfo = (HBox) container.getChildren().get(i);

            //Set entry labels to show the corresponding value from the database
            Label subject_Label = (Label) messageInfo.getChildren().get(0);
            subject_Label.setText(list.get(i).get("message_subject").toString());

            //FIXME: Set sender to be autenticated user, not just first user in DB
            Label sender_Label = (Label) messageInfo.getChildren().get(2);
            User user = (User) User.findAll().get(0);
            sender_Label.setText(user.get("email").toString());

            Label date_Label = (Label) messageInfo.getChildren().get(3);
            date_Label.setText(list.get(i).get("created_at").toString());
        }
    }

    public void deleteSentMessage(int index)
    {
        //delete from database
        List<Message> messages = Message.findAll();
        Message messageToDelete = messages.get(index);
        messageToDelete.delete();

        //delete entry
        container.getChildren().remove(index);
    }

<<<<<<< HEAD
    public void sendSMS (String text)  {

        NexmoProvider n = new NexmoProvider();

        try {
            n.sendSMS("+4530703294", "+4561307580", text);
            System.out.println("SMS blev sendt");

        } catch (Exception e) {
            System.out.println("SMS blev ikke sendt");
        }

    }


    public void sendMessage(int groupId)
    {
        List<Member> membersInGroup = Member.where("group_id =?", groupId);

        //Find all the members in a group
        for (int i = 0; i < membersInGroup.size(); i++)
        {
            Member member = membersInGroup.get(i);

            //Get their corresponding emails.
            ContactPerson contactPerson = ContactPerson.findFirst("id =?", member.get("contact_person_id"));
            String recipientEmail = contactPerson.get("email").toString();
            String recipientSMS = contactPerson.get("phone").toString();

            System.out.println("email test");
            if(email_CheckBox){
                    Notification notification = new Notification(
                            "test@example.com",
                            recipientEmail,
                            textField.getText(),
                            textArea.getText()
                    );
                try {

                    notification.sendMail();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("sms test");
            if (text_CheckBox) {

                System.out.println("sms test");
                System.out.println("test");

                Notification notifi = new Notification(
                        "+4530703294",
                        recipientSMS,
                        textField.getText(),
                        textArea.getText()
                );
                notifi.sendSMS();
            }
        }
=======
    public void sendMessage(int groupId, boolean mail, boolean text)
    {
        //saveMessage(email_CheckBox,);
        SendService emailThread = new SendService(groupId, mail, text, textField, textArea);
        emailThread.setDaemon(true);
        emailThread.start();
>>>>>>> origin/master
    }
}

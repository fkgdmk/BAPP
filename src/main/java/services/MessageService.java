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
 * Created by Jakob Larsen on 20-05-2017.
 */
public class MessageService
{
    private JFXTextArea textArea; //message
    private JFXTextField textField; //subject
    Label dateLabel;
    Label errorLabel;
    private VBox container;

    public void setTextArea(JFXTextArea _textArea) {
        textArea = _textArea;
    }

    public void setErrorLabel(Label _errorLabel) {
        errorLabel = _errorLabel;
    }

    public void setTextField(JFXTextField _textField) {
        textField = _textField;
    }

    public void setDateLabel(Label _dateLabel) {
        dateLabel = _dateLabel;
    }

    public void setSentMessagesContainer(VBox _container) {
        container = _container;
    }

    public void saveMessage(boolean email_CheckBox, boolean text_CheckBox, boolean facebook_CheckBox)
    {
        MessageTableSeeder messageTableSeeder = new MessageTableSeeder();
        messageTableSeeder.Seed(textArea.getText(), textField.getText(),
                email_CheckBox, text_CheckBox, facebook_CheckBox);
    }

    public void showSentMessages() {
        //Get messages in DB and store them in a list
        List<Message> list = Message.findAll();

        //Count message entries in VBox
        int amountOfChildren = container.getChildren().size();
        //Count messages in database
        int amountOfMessages = list.size();
        //Calculate how many many message entries need to be generated
        int difference = amountOfMessages - amountOfChildren;

        //If we have more messages in the database, than there are
        //entries (HBoxes) in the container (VBox), we have to create new ones.
        if (difference > 0) {
            System.out.println("There is a difference of " + difference + ". Creating new entries...");
            //Create message entries as needed
            for (int i = 0; i < difference; i++) {
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

                container.getChildren().add(amountOfMessages - (difference), newHBox);
            }
        }

        //Recalculate amount of message entries (because we just generated new ones)
        amountOfChildren = container.getChildren().size();

        //Loop through all VBox-entries
        for (int i = 0; i < amountOfChildren; i++) {
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

    public void sendMessage(boolean mail, boolean text, List<Integer> groupIDs)
    {
        //saveMessage(email_CheckBox,);
        SendService emailThread = new SendService(mail, text, textField, textArea, groupIDs);
        emailThread.setDaemon(true);
        emailThread.start();

    }
}


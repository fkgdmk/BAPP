package services;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Message;
import seeders.MessageTableSeeder;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Jakob on 20-05-2017.
 */
public class MessageService
{
    public JFXTextArea textArea;
    public Label dateLabel;
    public VBox container;

    public void setTextArea(JFXTextArea _textArea)
    {
        textArea = _textArea;
    }

    public void setDateLabel(Label _dateLabel)
    {
        dateLabel = _dateLabel;
    }

    public void setSentMessagesContainer(VBox _container)
    {
        container = _container;
    }

    public void saveMessage()
    {
        MessageTableSeeder messageTableSeeder = new MessageTableSeeder();
        messageTableSeeder.Seed(textArea.getText());
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
            subject_Label.setText(list.get(i).get("message").toString());

            //FIXME: Set database values (currently hardcoded)
            Label recipient_Label = (Label) messageInfo.getChildren().get(1);
            recipient_Label.setText("Modtager");

            //FIXME: Set database values (currently hardcoded)
            Label sender_Label = (Label) messageInfo.getChildren().get(2);
            sender_Label.setText("Afsender");

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

    public void sendMessage()
    {
        //send message
    }
}

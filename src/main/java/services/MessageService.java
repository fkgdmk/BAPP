package services;

import com.jfoenix.controls.JFXTextArea;
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

    public void changeDateLabel(Timestamp date)
    {
        dateLabel.setText(date.toString());
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

        //Count of message entries in VBox
        int amountOfChildren = container.getChildren().size();
        System.out.println("AOC: " + amountOfChildren);
        //Check to see how many iterations of the database messages is needed
        int amountOfMessages = list.size();
        System.out.println("AOM: " + amountOfMessages);
        //Get difference
        int difference = amountOfMessages - amountOfChildren;

        //If we have more messages in the database, than there are
        //entries (HBoxes) in the container (VBox), we have to create new ones.
        if (difference > 0)
        {
            System.out.println("There is a difference of: " + difference + ". Creating new entries...");
            for (int i = 0; i < difference; i++)
            {
                HBox newHBox = new HBox();
                Label sub = new Label();
                Label rec = new Label();
                Label sen = new Label();
                Label dat = new Label();
                newHBox.getChildren().addAll(sub, rec, sen, dat);

                container.getChildren().add(amountOfMessages-(difference), newHBox);
            }
        }

        System.out.println("AOC: " + amountOfChildren);
        amountOfChildren = container.getChildren().size();
        //Loop through all VBox-entries
        for (int i = 0; i < amountOfChildren; i++)
        {
            //Grab an entry (child @ i)
            HBox messageInfo = (HBox) container.getChildren().get(i);

            Label subject_Label = (Label) messageInfo.getChildren().get(0);
            subject_Label.setText(list.get(i).get("message").toString());

            Label date_Label = (Label) messageInfo.getChildren().get(3);
            date_Label.setText(list.get(i).get("created_at").toString());

            //Grab a message (index x in the list of messages)
            //grab the data of the message
            //set subject of child 0 (subject field) to be that of the message in the database

            //subject.setText(messageInstance.);

            //set recipient of child 1 to be that of the message in the database

            //set sender of child 2 to be that of the message in the database

            //set date of child 3 (date field) to be that of the message in the database
        }
    }

    public void sendMessage()
    {
        //send message
    }
}

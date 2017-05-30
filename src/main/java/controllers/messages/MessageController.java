package controllers.messages;

import App.Main;
import Notifications.Providers.SMS.NexmoProvider;
import com.jfoenix.controls.*;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Message;
import services.MessageService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakob Larsen on 18/05/2017.
 */
public class MessageController
{
    @FXML
    public JFXButton newMessage;
    public JFXButton messageHistory;
    public JFXButton messageTemplates;
    public JFXButton sendMessage;
    public JFXTextArea messageTextArea;
    public Label date;
    public VBox sentMessagesContainer;
    public JFXTextField subjectTextField;
    public JFXCheckBox email_CheckBox;
    public JFXCheckBox text_CheckBox;
    public JFXCheckBox facebook_CheckBox;
    public JFXToggleButton sendToGroup1Button;
    public JFXToggleButton sendToGroup2Button;
    public JFXToggleButton sendToGroup3Button;

    public Label errorLabel;


    public boolean sendEmail = false;
    public boolean sendText = false;
    public boolean sendFacebook = false;
    public boolean sendToGroup1 = false;
    public boolean sendToGroup2 = false;
    public boolean sendToGroup3 = false;

    public static MessageService messageService;


    public void initialize()
    {
        messageService = new MessageService();
        messageService.setTextArea(messageTextArea);
        messageService.setDateLabel(date);
        messageService.setSentMessagesContainer(sentMessagesContainer);
        messageService.setTextField(subjectTextField);
        messageService.setErrorLabel(errorLabel);
    }

    public void sendNewMessage () throws Exception {

        NexmoProvider n = new NexmoProvider();

        n.sendSMS("+4530703294", "+4561307580", "Hva så");
    }

    @FXML
    private void handleSentMessages()
    {
        getMessageContainer();
        Timestamp timeStamp;
        long count = Message.count();
        System.out.println("Count: " + count);
        Message message = Message.findFirst("created_at");
        if (message != null)
        {
            timeStamp = (Timestamp) message.get("created_at");
            System.out.println("TS: " + timeStamp);
            //messageService.changeDateLabel(timeStamp);
            messageService.showSentMessages();
        }
        else
        {
            System.out.println("Something went wrong with handling Sent Messages");
        }
    }

    @FXML
    private void handleCheckBoxes(ActionEvent event)
    {
        ButtonBase button = (ButtonBase) event.getSource();
        System.out.println("Source: " + button.getId().toString());

        switch (button.getId())
        {
            case "email_CheckBox":
                sendEmail = email_CheckBox.isSelected();
                break;
            case "text_CheckBox":
                sendText = text_CheckBox.isSelected();
                break;
            case "facebook_CheckBox":
                sendFacebook = facebook_CheckBox.isSelected();
                break;
            case "sendToGroup1Button":
                sendToGroup1 = sendToGroup1Button.isSelected();
                break;
            case "sendToGroup2Button":
                sendToGroup2 = sendToGroup2Button.isSelected();
                break;
            case "sendToGroup3Button":
                sendToGroup3 = sendToGroup3Button.isSelected();
                break;
        }
    }

    @FXML
    private void getMessageContainer()
    {
        VBox container = messageService.container;
        System.out.println(container.getChildren().size());
    }

    @FXML
    private void handleSendMessage(ActionEvent event) throws Exception
    {
        List<Integer> groupIDs = new ArrayList<>();

        if (event.getSource() == sendMessage)
        {
            int id = 0;

            if (sendToGroup1)
            {
                groupIDs.add(1);
            }
            if (sendToGroup2)
            {
                groupIDs.add(2);
            }
            if (sendToGroup3)
            {
                groupIDs.add(3);
            }

            if (subjectTextField.getText().isEmpty() == false)
            {
                if (sendEmail || sendText || sendFacebook)
                {

                    messageService.saveMessage(sendEmail, sendText, sendFacebook);
                    messageService.sendMessage(id, sendEmail, sendText, groupIDs);
                } else {
                    System.out.println("Please select media.");
                    errorLabel.setText("Vælg venligst et medie");
                    errorLabel.setVisible(true);
                }
            }

            else
            {
                System.out.println("Please insert subject");
                errorLabel.setText("Indtast venligst et emne");
                errorLabel.setVisible(true);
                System.out.println("Please select media.");
            }
        }
    }


    @FXML
    private void handleTabNavigation (ActionEvent event) throws Exception
    {
        BorderPane rootLayout =  Main.getRootLayout();

        if (event.getSource() == newMessage) {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/messages/messages.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == messageHistory)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/messages/history.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
            handleSentMessages();
        }
        if(event.getSource() == messageTemplates)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/messages/templates.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }

    }

}

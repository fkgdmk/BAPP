package controllers.messages;

import App.Main;
import Notifications.Providers.SMS.NexmoProvider;
import com.jfoenix.controls.*;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Message;
import services.MessageService;

import java.sql.Timestamp;

/**
 * Created by jasonkelly on 18/05/2017.
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
        if (event.getSource() == email_CheckBox)
        {
            if (sendEmail)
            {
                sendEmail = false;
                System.out.println("E:" + sendEmail);
            }
            else
            {
                sendEmail = true;
                System.out.println("E:" + sendEmail);
            }
        }

        if (event.getSource() == text_CheckBox)
        {
            if (sendText)
            {
                sendText = false;
                System.out.println("T:" + sendText);
            }
            else
            {
                sendText = true;
                System.out.println("T:" + sendText);
            }
        }

        if (event.getSource() == facebook_CheckBox)
        {
            if (sendFacebook)
            {
                sendFacebook = false;
                System.out.println("F:" + sendFacebook);
            }
            else
            {
                sendFacebook = true;
                System.out.println("F:" + sendFacebook);
            }
        }

        if (event.getSource() == sendToGroup1Button)
        {
            if (sendToGroup1)
            {
                sendToGroup1 = false;
                System.out.println("G1:" + sendToGroup1);
            }
            else
            {
                sendToGroup1 = true;
                System.out.println("G1:" + sendToGroup1);
            }
        }

        if (event.getSource() == sendToGroup2Button)
        {
            if (sendToGroup2)
            {
                sendToGroup2 = false;
                System.out.println("G2:" + sendToGroup2);
            }
            else
            {
                sendToGroup2 = true;
                System.out.println("G2:" + sendToGroup2);
            }
        }

        if (event.getSource() == sendToGroup3Button)
        {
            if (sendToGroup3)
            {
                sendToGroup3 = false;
                System.out.println("G3:" + sendToGroup3);
            }
            else
            {
                sendToGroup3 = true;
                System.out.println("G3:" + sendToGroup3);
            }
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
        if (event.getSource() == sendMessage)
        {
            int id;

            if (sendToGroup1)
            {
                id = 1;
            }
            else if (sendToGroup2)
            {
                id = 2;
            }
            else if (sendToGroup2)
            {
                id = 3;
            }
            else
            {
                id = 2;
            }

            messageService.sendMessage(id);
            if (subjectTextField.getText().isEmpty() == false)
            {
                if (sendEmail || sendText || sendFacebook)
                {                  
                    messageService.saveMessage(sendEmail, sendText, sendFacebook, sendToGroup1,
                        sendToGroup2, sendToGroup3);
                    messageService.sendMessage();
                }

                else
                {
                    System.out.println("Please select media.");
                    errorLabel.setText("Vælg venligst et medie");
                    errorLabel.setVisible(true);
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
    private void handleTabNavigation(ActionEvent event) throws Exception
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

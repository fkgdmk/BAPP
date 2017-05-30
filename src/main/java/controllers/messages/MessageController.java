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
    //FXML fields
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

    //Message selection booleans
    private boolean sendEmail = false;
    private boolean sendText = false;
    private boolean sendFacebook = false;
    private boolean sendToGroup1 = false;
    private boolean sendToGroup2 = false;
    private boolean sendToGroup3 = false;

    private static MessageService messageService;

    public void initialize()
    {
        //Create a new MessageService to handle most of the logic
        messageService = new MessageService();
        //Pass often-used fields for easier use
        messageService.setTextArea(messageTextArea);
        messageService.setDateLabel(date);
        messageService.setSentMessagesContainer(sentMessagesContainer);
        messageService.setTextField(subjectTextField);
        messageService.setErrorLabel(errorLabel);
    }

    @FXML
    private void handleSentMessages()
    {
        //If there are messages in the database, we want to show them.
        if (Message.count() != null)
        {
            //Tell message service to show the sent messages in the DB.
            messageService.showSentMessages();
        }
        //If there are no messages, we do nothing.
        else
        {
            System.out.println("There are no messages to show.");
        }
    }

    @FXML
    private void handleCheckBoxes(ActionEvent event)
    {
        //We get the check-button that was pressed.
        //We cast to ButtonBase, because we use two different check-boxes,
        //including JFXToggleButton and JFXCheckBox, which both derive from
        //ButtonBase.
        ButtonBase button = (ButtonBase) event.getSource();

        //We determine which button has been pressed, by comparing the ID
        //of the button that has been pressed in a switch-statement.
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
    private void handleSendMessage(ActionEvent event) throws Exception
    {
        //Create a list to store potentially more group IDs.
        List<Integer> groupIDs = new ArrayList<>();

        //Check if the button user pressed is "Send"
        if (event.getSource() == sendMessage)
        {
            //Make sure there are at least one group selected.
            if (sendToGroup1 || sendToGroup2 || sendToGroup3)
            {
                //Then add the groups selected to the list og group IDs.
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

                //Make sure the subject field is filled.
                if (!subjectTextField.getText().isEmpty())
                {
                    //Make sure a media is chosen.
                    if (sendEmail || sendText || sendFacebook)
                    {
                        //If all of the above is true, we are allowed to send a message.

                        //We store the message in the DB.
                        messageService.saveMessage(sendEmail, sendText, sendFacebook);
                        //We send the required variables to the message-service.
                        messageService.sendMessage(sendEmail, sendText, groupIDs);
                    }
                    else
                    {
                        errorLabel.setText("Vælg venligst et medie");
                        errorLabel.setVisible(true);
                    }
                }
                else
                {
                    errorLabel.setText("Indtast venligst et emne");
                    errorLabel.setVisible(true);
                }
            }
            else
            {
                errorLabel.setText("Vælg venligst mindst en gruppe");
                errorLabel.setVisible(true);
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

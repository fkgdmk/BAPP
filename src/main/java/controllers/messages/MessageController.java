package controllers.messages;

import App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
    

    public static MessageService messageService;



    public void initialize()
    {
        messageService = new MessageService();
        messageService.setTextArea(messageTextArea);
        messageService.setDateLabel(date);
        messageService.setSentMessagesContainer(sentMessagesContainer);
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
            messageService.saveMessage();
            messageService.sendMessage();
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

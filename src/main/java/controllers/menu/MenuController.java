package controllers.menu;

import App.Main;
import com.jfoenix.controls.JFXButton;
import controllers.groups.GroupController;
import controllers.members.MemberController;
import controllers.messages.MessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;


public class MenuController {


    public JFXButton groups;
    public JFXButton messages;
    public JFXButton members;

    @FXML
    private void SignOut(ActionEvent event) throws Exception
    {
        showLoginScreen();
    }


    @FXML
    private void changePage(ActionEvent event) throws Exception
    {
        BorderPane rootLayout =  Main.getRootLayout();

        if (event.getSource() == members) {
            setActive(members);
            FXMLLoader loader = new FXMLLoader(MemberController.class.getResource("/views/members/members.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == messages)
        {
            setActive(messages);
            FXMLLoader loader = new FXMLLoader(MessageController.class.getResource("/views/messages/messages.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == groups)
        {
            setActive(groups);
            FXMLLoader loader = new FXMLLoader(GroupController.class.getResource("/views/groups/groups.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }

    }


    public void setActive (JFXButton active)
    {
        members.setStyle("-fx-background-color: #ffffff");
        messages.setStyle("-fx-background-color: #ffffff");
        groups.setStyle("-fx-background-color: #ffffff");

        active.setStyle("-fx-background-color: #9fccb7");

    }




    public void showLoginScreen() {
        try {
            //   Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuController.class.getResource("/views/auth/login.fxml"));
            AnchorPane loginScreen = loader.load();

            BorderPane rootLayout =  Main.getRootLayout();
            Stage primaryStage = Main.getPrimaryStage();

            rootLayout.setLeft(null);
            rootLayout.setCenter(loginScreen);

            // Set width and height
            if (!primaryStage.isFullScreen())
            {
                primaryStage.setHeight(600);
                primaryStage.setWidth(600);
                primaryStage.centerOnScreen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

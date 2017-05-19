package controllers.auth;

import App.Main;
import com.jfoenix.controls.JFXButton;
import controllers.menu.MenuController;
import controllers.messages.MessageController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    public JFXButton submit;


    /**
     * Runs at the init of the class
     *
     * @throws Exception
     */
    @FXML
    private void Submit(ActionEvent event) throws Exception
    {
        showHomeScreen();
    }

    /**
     * Shows the homescreen screen
     */
    public void showHomeScreen() {
      try {
         //   Load person overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MessageController.class.getResource("/views/messages/messages.fxml"));
           AnchorPane loginScreen = loader.load();

           BorderPane rootLayout =  Main.getRootLayout();
           Stage primaryStage = Main.getPrimaryStage();

           showLeftMenu();

           rootLayout.setCenter(loginScreen);

          // Set width and height
          if (!primaryStage.isFullScreen()) {
              primaryStage.setHeight(600);
              primaryStage.setWidth(1200);
              primaryStage.centerOnScreen();
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLeftMenu() throws IOException {
        FXMLLoader leftMenu = new FXMLLoader(MenuController.class.getResource("/views/layout/leftMenu.fxml"));
        AnchorPane leftMenuView = leftMenu.load();

        BorderPane rootLayout =  Main.getRootLayout();
        rootLayout.setLeft(leftMenuView);
    }

}
package controllers.auth;

import App.Main;
import com.jfoenix.controls.JFXButton;
import controllers.home.HomeController;
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
           loader.setLocation(HomeController.class.getResource("/views/pages/messages.fxml"));
           AnchorPane loginScreen = loader.load();

           BorderPane rootLayout =  Main.getRootLayout();
           Stage primaryStage = Main.getPrimaryStage();

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

}
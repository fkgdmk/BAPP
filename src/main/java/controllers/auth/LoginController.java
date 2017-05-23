package controllers.auth;

import App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controllers.menu.MenuController;
import controllers.messages.MessageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.AuthService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;


public class LoginController {

    public JFXButton submit;
    public JFXPasswordField userPassword;
    public JFXTextField userName;
    public Text invalidCredentials;


    /**
     * Runs at the init of the class
     *
     * @throws Exception
     */
    @FXML
    private void Submit(ActionEvent event) throws Exception
    {

        AuthService authenticate = new AuthService();
        boolean authenticated =  authenticate.Login(userName.getText(), userPassword.getText());

        if(authenticated) {
            showHomeScreen();
        } else {
            invalidCredentials.setVisible(true);
        }
    }

    /**
     * Shows the homescreen screen
     */
    public void showHomeScreen() {
      try {

          System.out.println(new Timestamp(new Date().getTime()));
         //   Load person overview.
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MessageController.class.getResource("/views/messages/messages.fxml"));
           AnchorPane loginScreen = loader.load();

           BorderPane rootLayout =  Main.getRootLayout();
           Stage primaryStage = Main.getPrimaryStage();

            showLeftMenu();

          // Set width and height
          if (!primaryStage.isFullScreen()) {
              primaryStage.setHeight(600);
              primaryStage.setWidth(1200);
              primaryStage.centerOnScreen();
          }
          System.out.println(new Timestamp(new Date().getTime()));
            rootLayout.setCenter(loginScreen);

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
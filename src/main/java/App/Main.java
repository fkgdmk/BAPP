package App;

import Notifications.Providers.SMS.NexmoProvider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.javalite.activejdbc.Base;

import java.io.IOException;

public class Main extends Application{

    private static Stage primaryStage;
    private static BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {


        Base.open();
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("BAPP");

        initRootLayout();

        showLoginScreen();

        ConsoleTools tools = new ConsoleTools();
        tools.setDaemon(true);
        tools.start();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout()
    {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/layout/rootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/images/logo.png"));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the login screen
     */
    public void showLoginScreen()
    {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/auth/login.fxml"));
            AnchorPane loginScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(loginScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Returns the main stage.
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static BorderPane getRootLayout() {
        return rootLayout;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
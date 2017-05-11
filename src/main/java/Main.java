package main.java;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.javalite.activejdbc.Base;

public class Main extends Application{

    private static Stage stage;

    public static Stage getstage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Base is the database connection
        Base.open();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getClassLoader().getResource("views/login/login.fxml"));
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.toFront();
        stage.sizeToScene();
        stage.show();

        Main.stage = stage;


    }


    public static void main(String[] args) throws Exception{

        /*
         * @param args
         * @return Login instance
         */

        launch(args);

    }



}
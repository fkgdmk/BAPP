package controllers.home;

import App.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by jasonkelly on 18/05/2017.
 */
public class HomeController {

      public void initialize() throws Exception {

          FXMLLoader leftMenu = new FXMLLoader(HomeController.class.getResource("/views/layout/leftMenu.fxml"));
          AnchorPane leftMenuView = leftMenu.load();

          BorderPane rootLayout =  Main.getRootLayout();
          rootLayout.setLeft(leftMenuView);

    }

}

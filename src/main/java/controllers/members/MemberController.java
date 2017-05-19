package controllers.members;

import App.Main;
import com.jfoenix.controls.JFXButton;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by jasonkelly on 18/05/2017.
 */
public class MemberController {


    public JFXButton overviewButton;
    public JFXButton createMemberButton;


    @FXML
    private void handleTabNavigation(ActionEvent event) throws Exception
    {
        BorderPane rootLayout =  Main.getRootLayout();

        if (event.getSource() == createMemberButton) {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/members/members.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == overviewButton)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/members/overview.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }

    }

}

package controllers.groups;

import App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.GroupService;

/**
 * Created by jasonkelly on 18/05/2017.
 */
public class GroupController {


    public JFXButton newMessage;
    public JFXButton messageHistory;
    public JFXButton messageTemplates;
    public JFXListView listVIew;

    public void initialize () {
        GroupService groupService = new GroupService();
        groupService.setListView(listVIew);
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
        }
        if(event.getSource() == messageTemplates)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/messages/templates.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }

    }

}

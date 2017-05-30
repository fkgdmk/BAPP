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
 * Created by Fredrik Lippert on 18/05/2017.
 */
public class GroupController {


    public JFXListView listVIew;
    public JFXButton group1;
    public JFXButton group2;
    public JFXButton group3;
    private GroupService groupService;


    public void initialize () {
        this.groupService = new GroupService();
        this.groupService.setListView(listVIew, group1.getParent().getId().toString());
    }


    @FXML
    private void handleTabNavigation(ActionEvent event) throws Exception
    {
        BorderPane rootLayout =  Main.getRootLayout();

        if (event.getSource() == group1) {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/groups/groups.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == group2)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/groups/group2.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == group3)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/groups/group3.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }

    }

}

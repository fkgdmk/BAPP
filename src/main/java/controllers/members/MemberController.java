package controllers.members;

import App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import jdk.nashorn.internal.runtime.ECMAException;
import seeders.MemberTableSeeder;
import services.MemberService;

/**
 * Created by jasonkelly on 18/05/2017.
 */
public class  MemberController {


    public JFXButton overviewButton;
    public JFXButton createMemberButton;
    public JFXTextField firstname;
    public JFXTextField lastname;
    public JFXTextField phoneNumber;
    public JFXTextField email;
    public JFXComboBox pickGroup;
    public JFXButton addMember;

    public void initialize () {

        if(pickGroup != null) {


            MemberService service = new MemberService();

            service.setGroupPicker(pickGroup);

        }

    }

    @FXML
    private void addMember (ActionEvent event) throws Exception
    {

        if (event.getSource() == addMember) {

            MemberTableSeeder mSeed = new MemberTableSeeder();

            mSeed.Seed(firstname.getText(), 30703294);

        }


    }


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

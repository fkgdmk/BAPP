package controllers.members;

import App.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controllers.menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import services.ContactService;
import services.MemberService;

/**
 * Created by Fredrik Mikkelsen on 18/05/2017.
 */
public class  MemberController
{

    public JFXButton overviewButton;
    public JFXButton createMemberButton;
    public JFXTextField firstname;
    public JFXTextField lastname;
    public JFXTextField phoneNumber;
    public JFXTextField email;
    public JFXComboBox pickGroup;
    public JFXButton addMember;
    public Label statusLabel;
    public Label mailLabel;
    public Label phoneLabel;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Label groupLabel;
    public JFXTextField searchField;
    public JFXButton searchButton;
    public AnchorPane memberAnchor;
    public AnchorPane contactAnchor;
    public Label notFoundLabel;
    public JFXButton deleteMember;
    public JFXButton editMemberButton;
    public JFXTextField newName;
    public JFXComboBox pickContact;
    public JFXTextField newGroup;
    public JFXTextField newPhoneNr;
    public JFXTextField newEmail;
    public JFXCheckBox newContactCheckBox;
    public JFXButton cancelNewInfo;


    public void initialize ()
    {

        if(pickGroup != null)
        {
            MemberService service = new MemberService();
            service.setGroupPicker(pickGroup);
        }

        if (pickContact != null)
        {
            ContactService cService = new ContactService();
            cService.setContactPicker(pickContact);
        }
    }

    @FXML
    private Object searchForMember () throws Exception
    {

            MemberService mService = new MemberService();
            boolean searchStatus = mService.searchForNameInDB(searchField.getText());

            if (searchStatus)
            {
                firstnameLabel.setText(mService.getFirstName());
                lastnameLabel.setText(mService.getLastName());
                mailLabel.setText(mService.getMail());
                phoneLabel.setText("+45" + mService.getPhone());
                groupLabel.setText(mService.getGroup());

                memberAnchor.setVisible(true);
                contactAnchor.setVisible(true);

                notFoundLabel.setVisible(false);

                return searchField.getText();

            } else
                {
                    memberAnchor.setVisible(false);
                    contactAnchor.setVisible(false);

                    notFoundLabel.setVisible(true);
                }

        return "";

    }

    @FXML
    private void deleteMember () throws Exception
    {

       MemberService mService = new MemberService();
       boolean status = mService.deleteMemberFromDb(searchForMember());

       if (status)
       {
           memberAnchor.setVisible(false);
           contactAnchor.setVisible(false);

           notFoundLabel.setText("Medlem slettet");
           notFoundLabel.setVisible(true);
       }
    }

    @FXML
    private void editMember () throws Exception
    {
        MemberService mService = new MemberService();

        mService.editMemberNameInDB(searchForMember().toString(), newName.getText());
    }


    @FXML
    private void showContactAnchor ()
    {

        if (newContactCheckBox.isSelected())
        {
            contactAnchor.setVisible(true);
            pickContact.setVisible(false);
        } else
            {
            contactAnchor.setVisible(false);
            pickContact.setVisible(true);
            }
    }


    @FXML
    private void addMemberAndContact(ActionEvent event) throws Exception
    {
            ContactService contactService = new ContactService();
            MemberService memberService = new MemberService();

            boolean checked = newContactCheckBox.isSelected();

            if (checked)
            {
                contactAnchor.setVisible(true);
                pickContact.setVisible(false);
                contactService.addContactToDB(email.getText(), phoneNumber.getText());

            }

            boolean memberAdded;

            try
            {
                memberAdded = memberService.addMember(
                        firstname.getText() +
                                " " +
                                lastname.getText(),
                                pickGroup.getSelectionModel().getSelectedItem().toString(),
                                email.getText(),
                                newContactCheckBox,
                                pickContact);
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
                memberAdded = false;
                statusLabel.setVisible(true);
            }

            if (memberAdded)
            {
                statusLabel.setStyle("-fx-text-fill: lime");
                statusLabel.setText("Medlem oprettet");
                statusLabel.setVisible(true);

                firstname.clear();
                lastname.clear();
                phoneNumber.clear();
                email.clear();

            } else
            {
                statusLabel.setVisible(true);
            }
    }




    @FXML
    private void handleTabNavigation(ActionEvent event) throws Exception
    {
        BorderPane rootLayout =  Main.getRootLayout();

        if (event.getSource() == createMemberButton)
        {
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
        if (event.getSource() == editMemberButton)
        {

            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/members/editMember.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }
        if(event.getSource() == cancelNewInfo)
        {
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/views/members/overview.fxml"));
            AnchorPane view = loader.load();
            rootLayout.setCenter(view);
        }


    }


}

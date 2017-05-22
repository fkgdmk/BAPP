package services;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;
import models.ContactPerson;
import models.Group;
import models.Member;

import java.util.List;


/**
 * Created by Fredrik on 19-05-2017.
 */

public class MemberService
{

    public boolean addMemberToDB (String name, String groupName, String contactEmail) {


        Member m = new Member();

            try {

                m.set("name", name);
                System.out.println("test2");
                Group group = Group.findById("name = " + groupName);

                System.out.println("groupid" + group.getId());
                m.set("group_id", group.get("group_id"));
                ContactPerson cp = ContactPerson.findFirst("email = " + contactEmail);
                m.set("contact_person_id", cp.get("id"));
                m.saveIt();

                System.out.println("Medlem oprettet");
                return true;

            } catch (Exception e) {
                return false;
            }


    }

    public void setGroupPicker (JFXComboBox<Label> pickGroup)
    {
        List<Group> groups = Group.findAll();

        for (Group g: groups) {
            pickGroup.getItems().add(new Label(g.get("name").toString()));

        }

    }



}

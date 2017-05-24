package services;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import models.ContactPerson;
import models.Group;
import models.Member;

import java.util.List;
import java.util.Objects;


/**
 * Created by Fredrik on 19-05-2017.
 */

public class MemberService
{
    String fullName;
    String firstName;
    String lastName;
    String phone;
    String email;
    String group;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhone() {
        return phone;
    }
    public String getMail() {
        return email;
    }
    public String getGroup() {
        return group;
    }


    public boolean addMemberToDB (String name, String groupName, String contactEmail, JFXCheckBox checkBox, JFXComboBox box)
    {

        Member m = new Member();

            try
            {
                m.set("name", name);
                System.out.println("test2");
                Group group = Group.findFirst("name = ?", groupName);
                m.set("group_id", group.get("id"));
                System.out.println("test3");

                boolean checked = checkBox.isSelected();

                if (checked)
                {
                    System.out.println("checkbox test");
                ContactPerson cp = ContactPerson.findFirst("email = ?", contactEmail);
                m.set("contact_person_id", cp.get("id"));

                } else
                {
                    ContactPerson c = ContactPerson.findFirst("email = ?", box.getSelectionModel().getSelectedItem().toString());
                    m.set("contact_person_id", c.get("id"));
                }


                System.out.println("test4");

                m.saveIt();
                System.out.println("Medlem oprettet");
                return true;

            }
            catch (Exception e)
            {
                System.out.println(e.getCause());
                return false;
            }
    }

    public boolean getExistingContactID (String email) {

        try {

            ContactPerson contactPerson = ContactPerson.findFirst("email = ?", email);
            Member member = new Member();
            member.set("contact_person_id", contactPerson.get("id"));

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean searchForNameInDB (String searchInput)
    {

        try
        {
            Member member = Member.findFirst("name = ?", searchInput);

            fullName = member.get("name").toString();
            String[] parts = fullName.split( " ");

            firstName = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
            lastName = parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1);

            group = member.get("group_id").toString();

            ContactPerson cp = ContactPerson.findFirst("id = ?", member.get("contact_person_id"));

            email = cp.get("email").toString();
            phone = cp.get("phone").toString();

            return true;

        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean editMemberNameInDB (String oldName, String newName)
    {

        try {
            Member member = Member.findFirst("name = ?", oldName);
            member.set("name", newName).saveIt();
            System.out.println("Navn ændret");

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean deleteMemberFromDb (Object member)
    {

        try
        {
        Member m = Member.findFirst("name = ? ", member);
        m.delete();
        return true;

        }
        catch (Exception e)
        {
            return false;
        }

    }




    public void setGroupPicker (JFXComboBox pickGroup)
    {
        List<Group> groups = Group.findAll();

        for (Group g: groups) {
            pickGroup.getItems().add(g.get("name").toString());

        }

    }



}

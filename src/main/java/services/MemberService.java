package services;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import models.ContactPerson;
import models.Group;
import models.Member;
import java.util.List;


/**
 * Created by Fredrik Mikkelsen on 19-05-2017.
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


    /**
     * Tilføjer informationerne til databasen ved hjælp af et Active Record statements og metoder, som kan læses om i rapporten.
     *
     */
    public boolean addMember(String name, String groupName, String contactEmail, JFXCheckBox checkBox, JFXComboBox box)
    {

        Member m = new Member();

            try
            {
                m.set("name", name);
                Group group = Group.findFirst("name = ?", groupName);
                m.set("group_id", group.get("id"));

                boolean checked = checkBox.isSelected();

                ContactPerson cp;

                if (checked)
                {
                    cp = ContactPerson.findFirst("email = ?", contactEmail);
                }
                else
                {
                    cp = ContactPerson.findFirst("email = ?", box.getSelectionModel().getSelectedItem().toString());
                }

                m.set("contact_person_id", cp.get("id"));
                m.saveIt();

                return true;

            }
            catch (Exception e)
            {
                System.out.println(e.getCause());
                return false;
            }
    }

    /**
     *Søger efter medlem i databasen ved brug af findFirst. Derefter bruger vi et array til at dele det fulde navn
     * op i to dele, fornavn og efternavn.
     */
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
        catch (Exception e)
        {
            return false;
        }
    }


    /**
     *Søger først efter medlem med findFirst, og sletter derefter medlemmet fra databasen.
     */

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




    /**
     *Først tilføjes grupperne fra databasen til en liste, og derefter tilføjes listen til en ComboBox.
     */
    public void setGroupPicker (JFXComboBox pickGroup)
    {
        List<Group> groups = Group.findAll();

        for (Group g: groups) {
            pickGroup.getItems().add(g.get("name").toString());

        }

    }



}

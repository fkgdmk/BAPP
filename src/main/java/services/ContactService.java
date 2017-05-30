package services;

import com.jfoenix.controls.JFXComboBox;
import models.ContactPerson;
import java.util.List;

/**
 * Created by Fredrik Mikkelsen on 22-05-2017.
 */
public class ContactService
{


    /**
     *Tilføjer kontakt til databasen med parametrene email og telefonnr.
     */
    public boolean addContactToDB (String email, String phone)
    {
        ContactPerson cp = new ContactPerson();

        try
        {
            cp.set("email", email);
            cp.set("phone", phone);
            cp.saveIt();

            return true;

        } catch (Exception e) {
            return false;
        }

    }



    /**
     *Tager først alle kontaktpersonernes navne fra databasen og tilføjer dem til en liste. Derefter tilføjes listen
     * til en comboBox.
     */
    public void setContactPicker (JFXComboBox pickContact)
    {
        List <ContactPerson> contactEmails = ContactPerson.findAll();

        for (ContactPerson c: contactEmails)
        {
            pickContact.getItems().add(c.get("email"));
        }
    }


}

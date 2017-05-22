package services;

import models.ContactPerson;

/**
 * Created by Fredrik on 22-05-2017.
 */
public class ContactService
{

    public boolean addContactToDB (String email, String phone) {


        ContactPerson cp = new ContactPerson();

        try {
            cp.set("email", email);
            cp.set("phone", phone);
            cp.saveIt();

            System.out.println("Contact oprettet");
            return true;

        } catch (Exception e) {
            return false;
        }

    }
}

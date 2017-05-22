package seeders;

import models.ContactPerson;

/**
 * Created by Fredrik on 19-05-2017.
 */
public class ContactTableSeeder
{


    public void Seed () {

        ContactPerson cp = new ContactPerson();
        cp.set("email", "John Manuel");
        cp.set("phone", 12345678);
        cp.saveIt();

    }
}

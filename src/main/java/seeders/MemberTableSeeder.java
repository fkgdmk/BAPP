package seeders;

import models.ContactPerson;

/**
 * Created by Fredrik on 19-05-2017.
 */
public class MemberTableSeeder
{

    public void Seed (String email, String phone) {

        ContactPerson cp = new ContactPerson();

        cp.set("email", email);
        cp.set("phone", phone);
        cp.saveIt();

    }
}

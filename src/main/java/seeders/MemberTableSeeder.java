package seeders;

import models.ContactPerson;
import models.Group;
import models.Member;
import models.User;

/**
 * Created by Fredrik on 19-05-2017.
 */
public class MemberTableSeeder
{

    public void Seed (String email, int phone) {

        ContactPerson cp = new ContactPerson();

        cp.set("email", email);
        cp.set("phone", phone);
        cp.saveIt();
    }
}

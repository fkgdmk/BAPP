package seeders;

import models.ContactPerson;
import models.Group;
import models.Member;

/**
 * Created by Fredrik on 19-05-2017.
 */
public class MemberTableSeeder
{

    public void Seed (String name, String email, String phone, String groupName) {

        ContactPerson cp = new ContactPerson();

        cp.set("email", email);
        cp.set("phone", phone);
        cp.saveIt();

        Member m = new Member();
        m.set("name", name);
        m.set("contact_person_id", cp.get("id"));
        Group group = Group.findFirst("name = ?", groupName);
        m.set("group id", group.get("id"));
    }
}

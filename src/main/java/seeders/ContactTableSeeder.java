package seeders;

import models.ContactPerson;

/**
 * Created by Fredrik on 19-05-2017.
 */
public class ContactTableSeeder
{


    /**
     *Tilføjer John Manuel til email kollennen og 1234567 til phone kollonnen i contact_persons tabellen.
     * Det samme sker i de andre seeders klasser, bare til andre tabeller i databasen.
     */
    public void Seed () {

        ContactPerson cp = new ContactPerson();
        cp.set("email", "John Manuel");
        cp.set("phone", 12345678);
        cp.saveIt();

    }
}

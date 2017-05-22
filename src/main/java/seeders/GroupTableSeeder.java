package seeders;

import models.Group;

/**
 * Created by Fredrik on 22-05-2017.
 */
public class GroupTableSeeder
{

    public void Seed ()
    {
        for (int i = 1; i <= 3; i++) {

            Group group = new Group();

            group.set("name", "Gruppe " + i);
            group.saveIt();

        }


    }
}

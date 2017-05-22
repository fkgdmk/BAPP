package seeders;

import models.Message;

/**
 * Created by Jakob on 19-05-2017.
 */
public class MessageTableSeeder
{
    public void Seed(String message)
    {
        Message m = new Message();
        System.out.println(message);
        m.set("message", message);
        m.saveIt();
    }
}

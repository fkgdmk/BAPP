package seeders;

import models.Message;

/**
 * Created by Jakob on 19-05-2017.
 */

public class MessageTableSeeder
{
    //Parameter to seed (or create) a message is required.
    public void Seed(String message, String subject, boolean e, boolean t, boolean f)
    {
        //We create a new Message (extends Model).
        Message m = new Message();

        //We set the values of the newly created message
        //with the values passed from the parameter.
        m.set("message", message);
        m.set("message_subject", subject);
        m.set("media_email", e);
        m.set("media_textmessage", t);
        m.set("media_facebook", f);

        //We save the message to the database.
        m.saveIt();
    }
}


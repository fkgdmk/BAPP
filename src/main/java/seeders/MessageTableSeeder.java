package seeders;

import models.Message;

/**
 * Created by Jakob on 19-05-2017.
 */
public class MessageTableSeeder
{
    public void Seed(String message, String subject, boolean e, boolean t, boolean f,
                     boolean g1, boolean g2, boolean g3)
    {
        Message m = new Message();
        System.out.println(message);
        m.set("message", message);
        m.set("message_subject", subject);
        m.set("media_email", e);
        m.set("media_textmessage", t);
        m.set("media_facebook", f);
        m.saveIt();
    }
}

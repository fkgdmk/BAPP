package seeders;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by jasonkelly on 23/05/2017.
 */

public class UserTableSeeder {

    public void Seed()
    {
        String hashed = BCrypt.hashpw("bapp", BCrypt.gensalt());
        User p = new User();
        p.set("name", "Pia Eriksen");
        p.set("email", "pia@bapp.dk");
        p.set("password", hashed);
        p.saveIt();
    }


}
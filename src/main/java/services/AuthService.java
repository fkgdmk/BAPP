package services;

import models.User;
import org.javalite.activejdbc.Model;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by jasonkelly on 23/05/2017.
 */

public class AuthService {

        private static Model user;

        /**
         *
         * @param email
         * @param password
         * @return
         */
        public boolean Login(String email, String password){

            User authUser = User.findFirst("email = ?",email);
            if(authUser != null){
                String hashedPassword = (String) authUser.get("password");
                if(BCrypt.checkpw(password, hashedPassword)){
                    user = authUser;
                    return true;
                }
            }

            return false;
        }

        public static boolean Logout(){
            user = null;
            return true;
        }

        public static Model user() {
            return user;
        }

}

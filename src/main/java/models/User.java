package models;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.List;

@Table("users")
public class User extends Model {
    public User() {}
}
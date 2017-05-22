package App;

import org.javalite.activejdbc.Base;
import seeders.ContactTableSeeder;
import seeders.GroupTableSeeder;
import seeders.UserTableSeeder;

import java.util.Scanner;

/**
 * Created by jasonkelly on 19/05/2017.
 */
public class ConsoleTools extends Thread {

    public void run() {
        Base.open();
        startConsoleOptions();

    }

    private boolean startConsoleOptions()
    {
        System.out.println("run command");
        Scanner scanner = new Scanner(System.in);
        String Command;
        boolean run = true;
        while(run) {
            Command = scanner.nextLine();

            switch (Command) {
                case "bapp:seed --user":
                    runUserSeeder();
                    break;
                case "bapp:end":
                    Base.close();
                    run = false;
                    System.out.println("Killed");
                    break;
                case "bapp:seed --contact":
                    runContactSeeder();
                    break;
                case "bapp:seed --group":
                    runGroupSeeder();
                    break;
                default: System.out.println("Command not found");
                    break;
            }
        }
        return false;
    }

    /**
     * Run database seeder
     */
    private void runUserSeeder ()
    {
        System.out.println("Running seeder");
        UserTableSeeder userSeed = new UserTableSeeder();
        userSeed.Seed();

        System.out.println("Seeder done");
    }

    private void runContactSeeder (){

        System.out.println("Running seeder");
        ContactTableSeeder contactSeeder = new ContactTableSeeder();
        contactSeeder.Seed();
        System.out.println("Seeder done");

    }

    private void runGroupSeeder () {

        System.out.println("Running seeder");
        GroupTableSeeder groupSeeder = new GroupTableSeeder();
        groupSeeder.Seed();
        System.out.println("Seeder done");

    }


}

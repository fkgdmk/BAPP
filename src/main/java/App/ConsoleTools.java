package App;

import org.javalite.activejdbc.Base;
import seeders.MemberTableSeeder;
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
                case "bapp:seed":
                    runSeeder();
                    break;
                case "bapp:end":
                    Base.close();
                    run = false;
                    System.out.println("Killed");
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
    private void runSeeder()
    {
        System.out.println("Running seeder");
        UserTableSeeder userSeed = new UserTableSeeder();
        userSeed.Seed();
        System.out.println("Seeder done");
    }


}

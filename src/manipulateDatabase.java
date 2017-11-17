
// THIS CLASS IS ONLY FOR THE SERVER ADMINISTRATORS
// ADDING AND REMOVING OF USERS IS DONE THROUGH THIS CLASS.
import java.util.Scanner;

public class manipulateDatabase {

    serverMain main;
    database d;

    Scanner s;

    boolean added;

    public manipulateDatabase() {

        try {

            d = new database();
            d.start();


            s = new Scanner(System.in);

            System.out.println("** What do you want to do to the database?***");
            System.out.println("Press:\n1 for adding user\n2 for deleting user\n3 for viewing the records");

            int r = s.nextInt();
            switch (r) {

                case 1: {

                    System.out.println("Please enter the username you want to add:");
                    s = new Scanner(System.in);
                    String uname = s.nextLine();

                    System.out.println(uname);

                    System.out.println("Please enter the password for the user:");
                    s = new Scanner(System.in);
                    String pwd = s.nextLine();

                    if (uname != null && pwd != null) {
                        added = d.add(uname, pwd);
                    } else {
                        System.out.println("Cant enter null");
                        added = false;
                    }

                    if (added) {
                        System.out.println("User added!\n");
                    }

                    manipulateDatabase m = new manipulateDatabase();
                    break;


                }
                case 2: {
                    System.out.println("Enter the name of user you want to delete;\n");
                    s = new Scanner(System.in);
                    String uname = s.nextLine();

                    d.remove(uname);
                    manipulateDatabase m = new manipulateDatabase();

                    break;

                }
                case 3: {


                    System.out.println("Users: ");
                    System.out.println(d.names + "\n");

                    System.out.println("Passwords: ");
                    System.out.println(d.password + "\n");
                    manipulateDatabase m = new manipulateDatabase();
                    break;


                }
                default: {
                    main = new serverMain();
                }

            }

            main = new serverMain();

        }
        catch(Exception ex){
            System.out.println("In manipulate databasse class: "+ ex);
        }
    }
}

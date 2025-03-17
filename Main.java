import java.io.File;
import java.util.Scanner;


// ideally tidy up the log in page and instead of trying to store an invalid option we will stop it there with a certain command
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //loaded my database in first
        LogInDatabase db = new LogInDatabase();
        MenuSchedular ms = new MenuSchedular(sc);

        // Check if database file exists
        File DatabaseFile = new File("SavedDatabase.dat");
        if (DatabaseFile.exists()) {
            System.out.println("Database file exists at: " + DatabaseFile.getAbsolutePath());
        } else {
            System.out.println("Database file does NOT exist. Creating a new one.");
        }



        int UserChoice = 0;


        do {

            ms.DisplayMenu();
            UserChoice=ms.getUserChoice();


            // this is a switch case for either entering the logIn Page or the SignUP Page or if a miustake is made to clear screen and loop back
            switch (UserChoice) {
                case 1:
                    LogInPage.LogIn(sc,db );
                    break;
                case 2:
                    SignUpPage.SignUp(sc,db);
                    break;
                default:
                    System.out.println("Invalid choice. Try again");

                    ClearScreen.clearScreen();
                    break;
            }

        }while( UserChoice < 1 || UserChoice > 2);
    }


}

//make the data from the hashtable to read and also export in a file so it won't just erase each time.
import java.util.Scanner;
// this is basically to deal with all things main menu so my main isnt so cluttered and it looks a lot better and easier so ik exactly where everyhting is supposed ot be
public class MenuSchedular{
    //private to prevent data security leaks
    private Scanner sc;
    private LogInDatabase db;

    // constructer
    public MenuSchedular(Scanner sc) {
        this.sc = sc;
        this.db = new LogInDatabase();
    }

    //displays the main menu and asks the user to enter their choice
    public void DisplayMenu() {
        while (true) {
            ScreenManager.ClearScreen();
            System.out.println("\nWelcome to the Automated CV Analyzer!");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");

            // uses the Invalidation then i can deal with if the user inputs something that isnt an int
            int UserChoice = IntValidation.ValidateInt("Enter your choice (1 or 3): ");


            // If user chooses Exit (3), break loop because it returns false
            if (!HandlesUserChoice(UserChoice)) {
                break;
            }
        }
    }

    private boolean HandlesUserChoice(int UserChoice){
        // this is a switch case for either entering the logIn Page or the SignUP Page or if a mistake is made to clear screen and loop back
        switch (UserChoice) {
            case 1:
                // goes to the sign in class
                LogInPage.LogIn(sc,db );
                break;
            case 2:
                // goes to the sign up class
                SignUpPage.SignUp(sc,db);
                break;

            case 3:
                // Quits the program
                System.out.print("Closing Program.....");
                return false;
            default:
                System.out.println("Invalid choice. Try again");
                break;
        }

        // keeps looping until 3 is chosen
        return true;


    }


}

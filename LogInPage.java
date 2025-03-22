import java.util.Scanner;

public class LogInPage {
    public static void LogIn(Scanner sc, LogInDatabase db) {
        // clears the screen to allow a better user experience
        ScreenManager.ClearScreen();

        String Username;
        boolean LogInSuccess = false;
        sc.nextLine();

        // if the file just been made and is empty then it will send you to the sign up page
        if(db.GetUserCredentialMap().isEmpty()){
            System.out.println("There are no user saved in the database.\n Redirecting to Sign up page....");
            SignUpPage.SignUp(sc, db);
        }

        // greets the user
        System.out.println("Welcome to the CV analyzer");

        do {
            // informs the user that it is Cap Sensitive
            System.out.println("WARNING: Passwords and username are cap sensitive");

            //asks for the username
            Username = StringValidation.ValidateString("Please enter your username:",sc);

            // checks if the username exists or not
            if (!db.GetUserCredentialMap().containsKey(Username)){
                System.out.println("Username not found");
                System.out.println("Try again or try a different username: ");
                continue;
            }


            //asks for the password
            String Password = StringValidation.ValidateString("Please enter your password:",sc);


            //uses the username to check if the stored password and the entered passwords are the same
            LogInSuccess = db.CheckPasswordIsValid(Username,Password);


          // keeps asking till LogInSuccess is true
        } while (!LogInSuccess);

        // goes to the dashboard redirector to send thme to the correct page
        DashboardRedirector.Redrict(Username,sc,db);

    }
}






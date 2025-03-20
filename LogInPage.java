import java.util.Scanner;

public class LogInPage {
    public static void LogIn(Scanner sc, LogInDatabase db) {
        ScreenManager.ClearScreen();
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
            String Username = StringValidation.ValidateString("Please enter your username:",sc);

            if (!db.GetUserCredentialMap().containsKey(Username)){
                System.out.println("Username not found");
                System.out.println("Try again or try a different username: ");
                continue;
            }


            //asks for the password
            String Password = StringValidation.ValidateString("Please enter your password:",sc);


            //compare with the linked list database to see if present
            LogInSuccess = db.CheckUserCredentials(Username,Password);


            if (LogInSuccess) {
                UserInfo User = db.GetUser(Username);
                String StoredAccountType = User.GetAccountType();

                DashboardRedirector.Redrict(StoredAccountType,Username);
            } else {
                System.out.println("Invalid username or password");
            }
        } while (!LogInSuccess);// keeps asking until  its true
    }
}






import java.util.Scanner;

public class LogInPage {
    public static void LogIn(Scanner sc, LogInDatabase db) {


        boolean LogInSuccess;
        do {
            // greets the user
            System.out.println("Welcome to the CV analyzer");
            System.out.println("WARNING: Passwords and username are cap sensitive");

            //asks for the username
            System.out.print("Please enter your username:");
            String Username = sc.nextLine();

            //asks for the password
            System.out.print("Please enter your password:");
            String Password = sc.nextLine();


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




 // next step is to send this to the special pages for recruiter and or applicants via the saved data
//maybe can use one of the main 4 pillars of oop to create this.
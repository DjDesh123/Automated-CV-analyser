import java.util.Scanner;

public class SignUpPage {

    public static void SignUp(Scanner sc, LogInDatabase db){
        System.out.println("Welcome to the sing up page");


        //Displays questions that are needed for the database


        System.out.println("Are you a recruiter or are you an applicant? (1 for Recruiter 2 for Applicant)");
        int AccountChoice = sc.nextInt();
        sc.nextLine(); // stops the issue of skipping the next input

        System.out.println("Please enter your username:");
        String Username = sc.nextLine();

        System.out.println("Please enter your password:");
        String Password = sc.nextLine();

        // shorter way to write if-else statments
        String AccountType = (AccountChoice == 1) ? "Recruiter" : "Applicant";


        db.AddEntry(Username,Password,AccountType);
        System.out.println("You have created an a new account with your username: " + Username);
        System.out.print(db);

        // TEMP to just see if the datbase works the way it was planned
        LogInPage.LogIn(sc,db);
    }

}


// after creating their accounts sending them to the right page
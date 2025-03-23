import java.util.Scanner;

public class SignUpPage {

    private static final int RECRUITER =  1;
    private static final int APPLICANT = 2;

    public static void SignUp(Scanner sc, LogInDatabase db){

        // initialized the variables for in the loop
        String Username;
        int AccountChoice;
        String AccountType="";

        // to improve user's ui experience
        ScreenManager.ClearScreen();

        // introduce them into the sign-up page
        System.out.println("\n\nWelcome to the sing up page\n\n");


        do{
            //stores the username
            Username=StringValidation.ValidateString("Please enter a username:",sc);

            // Linked hashmaps don't handle duplicate keys so preventing overwriting someone's account we have this if statement to warn them that the username is already taken
            if (db.GetUserCredentialMap().containsKey(Username)) {
                System.out.println("There is already a user with the same username");
            }

        }while(db.GetUserCredentialMap().containsKey(Username));


        //stores the password
        String Password=StringValidation.ValidateString("Please enter a password:",sc);


        // loops until the user enters a valid option
        do{
            System.out.println("Are you a:\n");
            System.out.println("1- Recruiter ");
            System.out.println("2- Applicant ");

            AccountChoice=IntValidation.ValidateInt("Enter your choice (1 or 2)",sc);

            switch(AccountChoice) {
                case RECRUITER:
                    AccountType = "Recruiter";
                    break;

                case APPLICANT:
                    AccountType = "Applicant";
                    break
                            ;
                default:
                    System.out.println("Invalid choice! Must enter 1 or 2");
            }

        }while(AccountChoice < RECRUITER || AccountChoice > APPLICANT);


        //adds the user's infomation into the database and displays a message after completion
        db.AddEntry(Username,Password,AccountType);
        System.out.println("You have created an a new account with your username: " + Username);

        //redirects them straight to the LogIn page
        System.out.println("you will be redriceted to the Login Page");
        LogInPage.LogIn(sc,db);
    }

}


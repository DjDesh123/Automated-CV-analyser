import java.util.Scanner;

public class RecruiterDashboard {

    // Constants for cleaner menu options
    private static final int ADD_JOB = 1;
    private static final int EDIT_JOB = 2;
    private static final int DELETE_JOB = 3;
    private static final int EXIT = 4;


    public static void ShowRecruiterDashboard(String Username,Scanner sc ) {

        JobDatabase jd = new JobDatabase();
        MenuSchedular ms = new MenuSchedular(sc);
        NotificationSystem ns = new NotificationSystem();


        int DashboardChoice;

        // removes all the loading database messages
        ScreenManager.ClearScreen();

        // greets the user
        System.out.println("\n\nWelcome to the Recruiter Dashboard \n\n");

        //displays all the notifications
        ns.ShowNotification(Username);

        // to check if the user has the any jobs saved in their database
        if (!jd.ShowUserJobs(Username)) {
            System.out.println("redirecting back to Add job menu .....");
            jd.AddJob(sc,Username);
        }

        do{

            //Displays the menu
            System.out.println("\nDo you wish to:");
            System.out.println("1. Add a job");
            System.out.println("2. Edit a job");
            System.out.println("3. Delete a job");
            System.out.println("4. Exit to main menu");

            DashboardChoice = IntValidation.ValidateInt("Enter your choice (1-4)", sc);

            // chose a switch statement instead of an if statement to make it neater and as well just want to tidy this code up with some cosntants for easier reading
            //I want to add a loop to this to make it look a lot better and keeps the flow of the project
            switch (DashboardChoice) {

                case ADD_JOB:
                    jd.AddJob(sc,Username);
                    break;

                case EDIT_JOB:
                    jd.EditJob(sc,Username);
                    break;

                case DELETE_JOB:
                    jd.DeleteJob(sc,Username);
                    break;

                case EXIT:
                    System.out.println("Redirecting to Main Menu....");
                    ms.DisplayMenu();
                    break;

                default:
                    System.out.println("Invalid choice! Must be an integer from 1 to 4");
                    ScreenManager.ClearScreen();

            }
        }while(DashboardChoice < ADD_JOB || DashboardChoice > EXIT);
    }


}
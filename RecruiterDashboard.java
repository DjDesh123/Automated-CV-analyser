import java.util.Scanner;

public class RecruiterDashboard {
    // Constants for cleaner menu options
    private static final int ADD_JOB = 1;
    private static final int EDIT_JOB = 2;
    private static final int DELETE_JOB = 3;
    private static final int EXIT = 4;


    public static void ShowRecruiterDashboard() {
        // greets the user
        System.out.print("Welcome to the Recruiter Dashboard");
        JobDatabase jd = new JobDatabase();
        LogInDatabase ld = new LogInDatabase();
        Scanner sc = new Scanner(System.in);
        MenuSchedular ms = new MenuSchedular(sc);



        // to check if the user has the any jobs saved in their database
        if (!jd.ShowAllJobs()) {
            System.out.println("No jobs found");
            System.out.println("redirecting back to Add job menu .....");
            jd.AddJob();
        }

        int DashboardChoice = IntValidation.ValidateInt("Do you wish to add, edit or delete job posting (1-4)");

        // chose a switch statement instead of an if statement to make it neater and as well just want to tidy this code up with some cosntants for easier reading
        //I want to add a loop to this to make it look a lot better and keeps the flow of the project
        switch (DashboardChoice) {
            case ADD_JOB:
                jd.AddJob();
                break;
            case EDIT_JOB:
                jd.EditJob();
                break;
            case DELETE_JOB:
                jd.DeleteJob();
                break;
            case EXIT:
                System.out.println("redriving back to main menu .....");
                ms.DisplayMenu();
                break;
            default:
                System.out.println("Invalid choice");

        }
    }

}







// need an if statement to see if the user has added any job posting previously
//need to add a new storage for that as i dont want it to be cluttered and i can have it link to the other database
// then have a list of the roles but i also need this database not to wipe each time i create something so need ot find a way of dealing with that
// but i can make it read data that we put in a file in the first place like my previosu project
// deciding to use dat files because it stores in binary can be encrypted and als0 preserve object state and is also layed out like a database


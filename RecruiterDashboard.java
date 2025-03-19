import java.util.Scanner;

public class RecruiterDashboard {
    public static void ShowRecruiterDashboard() {
        // greets the user
        System.out.print("Welcome to the Recruiter Dashboard");
        JobDatabase jd = new JobDatabase();
        LogInDatabase ld = new LogInDatabase();
        Scanner sc = new Scanner(System.in);


        // to check if the user has the any jobs saved in their database
        if (!jd.ShowAllJobs()) {
            System.out.println("No jobs found");
            System.out.println("redirecting back to Add job menu .....");
            AddJob.AddingJob();
        }

        int DashboardChoice = IntValidation.ValidateInt("Do you wish to add, edit or delete job posting (1-3)");

        // chose a switch statement instead of an if statement to make it neater and as well just want to tidy this code up with some cosntants for easier reading
        switch (DashboardChoice) {
            case 1:
                AddJob.AddingJob();
                break;
            case 2:
                DeleteJob.DeletingJob();
                break;
            case 3:
                EditJob.EditingJob();
        }
    }
}







// need an if statement to see if the user has added any job posting previously
//need to add a new storage for that as i dont want it to be cluttered and i can have it link to the other database
// then have a list of the roles but i also need this database not to wipe each time i create something so need ot find a way of dealing with that
// but i can make it read data that we put in a file in the first place like my previosu project
// deciding to use dat files because it stores in binary can be encrypted and als0 preserve object state and is also layed out like a database


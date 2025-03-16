import java.util.Scanner;

public class RecruiterDashboard {
    public static void ShowRecruiterDashboard(String Username) {
        // greets the user
        System.out.print("Welcome to the Recruiter Dashboard");
        JobDatabase jd = new JobDatabase();
        LogInDatabase ld = new LogInDatabase();
        Scanner sc = new Scanner(System.in);


        // to check if the user has the any jobs saved in their database
        if (!jd.ShowAllJobs()) {
            System.out.println("No jobs found");
            System.out.print("Do you want to add a job?(Y/N)");
            String AddJobChoice =sc.nextLine();

            // ignores if its upper or lowercase
            if (AddJobChoice.equalsIgnoreCase("Y")) {
                System.out.print("redirecting.....");
                AddJob JobAdder = new AddJob(jd);
                JobAdder.AddingJob();
            }
            else{
                System.out.print("Sending you back to the main menu");
                LogInPage.LogIn(sc,ld);

            }



        }
        else{
            jd.ShowAllJobs();
        }




    }
}







// need an if statement to see if the user has added any job posting previously
//need to add a new storage for that as i dont want it to be cluttered and i can have it link to the other database
// then have a list of the roles but i also need this database not to wipe each time i create something so need ot find a way of dealing with that
// but i can make it read data that we put in a file in the first place like my previosu project
// deciding to use dat files because it stores in binary can be encrypted and als0 preserve object state and is also layed out like a database


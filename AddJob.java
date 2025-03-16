import java.util.Scanner;
public class AddJob {
    private JobDatabase jdb; // ✅ Instance variable for the database

    // ✅ Constructor to pass the existing JobDatabase instance
    public AddJob(JobDatabase jdb) {
        this.jdb = jdb;
    }
    public void AddingJob() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to the adding jobs page ");

        //JobName
        System.out.println("Please enter the name of the job you would like to add: ");
        String JobName = sc.nextLine();
        //CompanyName
        System.out.println("Please enter the company name of the job you would like to add: ");
        String CompanyName = sc.nextLine();

        //Location
        System.out.println("Please enter the location of the job you would like to add: ");
        String Location = sc.nextLine();

        //Description
        System.out.println("Please enter the description of the job you would like to add: ");
        String Description = sc.nextLine();

        //Requirements
        // need to make this an array function to get it in the database
        System.out.println("Please enter the job requirements of the job you would like to add: ");
        String Requirements = sc.nextLine();


        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements);
        jdb.AddJob(NewJob);

        // the plan is for this file to simply get the data needed put it in the database and edge case it and make it neat
        //im liking try catch with the error messages so i want to keep implementing them as it makes debugging a lot easier
        // also note to self remove the debugging with the hashing and salt bc that's a big security issue.
    }
}

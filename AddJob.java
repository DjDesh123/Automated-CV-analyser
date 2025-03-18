import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AddJob {
    private JobDatabase jdb; // Instance variable for the database

    // onstructor to pass the existing JobDatabase instance
    public AddJob(JobDatabase jdb) {
        this.jdb = jdb;
    }

    // need to add methods to make the code cleaner and easier to understand
    public void AddingJob() {
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to the adding jobs page ");

        //JobName
        String JobName =StringValidation.ValidateString("Please enter the name of the job you would like to add: ");

        //CompanyName
        String CompanyName = StringValidation.ValidateString("Please enter the company name of the job you would like to add: ");


        //Location
        String Location = StringValidation.ValidateString("Please enter the location of the job you would like to add: ");


        //Description
        String Description=StringValidation.ValidateString("Please enter the description of the job you would like to add: ");


        //Requirements
        // need to make this an array function to get it in the database
        //Using the List<String> interface allows more flexibility to change the implementation
        List<String> Requirements = new ArrayList<>();

        while (true){
            String RequirementsInput =StringValidation.ValidateString("Enter job requirements. Type 'done' when finished:");



            // stops looping when done is written
            if (RequirementsInput.equalsIgnoreCase("done")){
                break;
            }

            // appends to requirements
            Requirements.add(RequirementsInput);
        }


        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements);
        jdb.AddJob(NewJob);

        // the plan is for this file to simply get the data needed put it in the database and edge case it and make it neat
        //im liking try catch with the error messages so i want to keep implementing them as it makes debugging a lot easier
        // also note to self remove the debugging with the hashing and salt bc that's a big security issue.
    }
}

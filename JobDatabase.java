import javax.print.attribute.standard.JobName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
// I want to add more notes and just tidy this class up a little its a bit sloppy
public class JobDatabase {

    private LinkedHashMap<String, JobData>  JobHashMap;

    // need to initalise the database from the file
    public JobDatabase() {
        JobHashMap=JobDatabaseStorage.LoadJobDatabase();
        System.out.println("Job Database Loaded");
    }

    public void SaveDatabase() {
        JobDatabaseStorage.SaveJobDatabase(JobHashMap);
    }

    // this method adds a job role to our database.
    public void AddJob() {


        JobDatabase jdb = new JobDatabase();
        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to the adding jobs page ");

        //JobName
        String JobName =StringValidation.ValidateString("Please enter the name of the job you would like to add: ");

        //CompanyName
        String CompanyName = StringValidation.ValidateString("Please enter the company name of the job you would like to add: ");


        //Location
        String Location = StringValidation.ValidateString("Please enter the location of the job you would like to add: ");


        //Description
        // scratch that change it back to StringValidation and remove the range
        // does not use the String validation as it has a limit of 2-15 characters long
        // could extend but then that would also be defeat teh purpose of it in other parts of the program
        System.out.println("Please enter the description of the job you would like to add: ");
        String Description = sc.nextLine();


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

        // Create a new JobData object
        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements);

        JobHashMap.put(JobName, NewJob);
        System.out.println("Job added: " + JobName);
        SaveDatabase();
    }

    // this method is to delete a job off our database
    public void DeleteJob(){
        Scanner sc =new Scanner(System.in);

        String DeletedJobName=StringValidation.ValidateString("Enter the name of the job you want to delete");

        if (JobHashMap.containsKey(DeletedJobName)){
            JobHashMap.remove(DeletedJobName);
            System.out.println("Job deleted successfully: " + DeletedJobName);
        }
        else {
            System.out.println("Job not found: " + DeletedJobName);
        }
        sc.close();

    }
    // this will show all the job in the beginning in the program
    public boolean ShowAllJobs(){
        if (JobHashMap.isEmpty()){
            System.out.println(JobHashMap.size());
            return false;
        }
        else {
            System.out.println("\nAll Jobs:");
            // LOOPS through the databse and displays all job names
            for (JobData jd : JobHashMap.values()) {
                System.out.println(jd.GetJobName());
            }
        }

        return true;
    }

    public void EditJob() {
        String GetJobName=StringValidation.ValidateString("Enter the name of the job you want to edit");

        if  (!JobHashMap.containsKey(GetJobName)){
            System.out.println("Job not found: " + GetJobName);
            return;

        }

        JobData jd = JobHashMap.get(GetJobName);

        // Display editable options
        System.out.println("What would you like to edit?");
        System.out.println("1. Job Title");
        System.out.println("2. Job Description");
        System.out.println("3. Salary");
        System.out.println("4. Requirements");
        System.out.println("5. Cancel");

        int choice = IntValidation.ValidateInt("Enter your choice (1-5)");

        switch (choice) {
            case 1:
                String newJobName = StringValidation.ValidateString("Enter the new job name");
                JobHashMap.remove(jd); // Remove old job
                jd = new JobData(newJobName, jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(newJobName, jd); // Reinsert with new key
                System.out.println("Job name updated successfully.");
                break;

            case 2:
                String newCompanyName = StringValidation.ValidateString("Enter the new company name");
                jd = new JobData(jd.GetJobName(), newCompanyName, jd.GetLocation(), jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Company name updated successfully.");
                break;

            case 3:
                String newLocation = StringValidation.ValidateString("Enter the new job location");
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), newLocation, jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Location updated successfully.");
                break;

            case 4:
                String newDescription = StringValidation.ValidateString("Enter the new job description");
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), newDescription, jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Description updated successfully.");
                break;

            case 5:
                List<String> newRequirements = new ArrayList<>();
                while (true) {
                    String requirement = StringValidation.ValidateString("Enter a requirement (or type 'done' to finish)");
                    if (requirement.equalsIgnoreCase("done")) break;
                    newRequirements.add(requirement);
                }
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), newRequirements);
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Requirements updated successfully.");
                break;

            case 6:
                System.out.println("Edit cancelled.");
                return;

            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

}


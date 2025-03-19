import java.util.*;

public class JobDatabase {
    private final LinkedHashMap<String, JobData> JobHashMap;

    // Initialize the database from the file
    public JobDatabase() {
        JobHashMap = JobDatabaseStorage.LoadJobDatabase();
        System.out.println("Job Database Loaded");
    }

    // Save database to file
    public void SaveDatabase() {
        JobDatabaseStorage.SaveJobDatabase(JobHashMap);
    }

    // Adds a job role to the database
    public void AddJob(Scanner sc) {
        System.out.println("Welcome to the job addition page");

        // JobName
        String JobName = StringValidation.ValidateString("Please enter the job name: ", sc);

        // CompanyName
        String CompanyName = StringValidation.ValidateString("Please enter the company name: ", sc);

        // Location
        String Location = StringValidation.ValidateString("Please enter the job location: ", sc);

        // Description
        System.out.println("Please enter the job description: ");
        String Description = sc.nextLine();

        // Requirements (Stored as a List)
        List<String> Requirements = new ArrayList<>();
        while (true) {
            String Requirement = StringValidation.ValidateString("Enter job requirement (type 'done' to finish):", sc);
            if (Requirement.equalsIgnoreCase("done")) break;
            Requirements.add(Requirement);
        }

        // Create and add the new job
        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements);
        JobHashMap.put(JobName, NewJob);
        System.out.println("Job added: " + JobName);
        SaveDatabase();
    }

    // Deletes a job from the database
    public void DeleteJob(Scanner sc) {
        String DeletedJobName = StringValidation.ValidateString("Enter the job name to delete:", sc);

        if (JobHashMap.containsKey(DeletedJobName)) {
            JobHashMap.remove(DeletedJobName);
            System.out.println("Job deleted successfully: " + DeletedJobName);
            SaveDatabase();
        } else {
            System.out.println("Job not found: " + DeletedJobName);
        }
    }

    // Displays all jobs in the database
    public boolean ShowAllJobs() {
        if (JobHashMap.isEmpty()) {
            System.out.println("No jobs found.");
            return false;
        } else {
            System.out.println("\nAll Jobs:");
            for (JobData jd : JobHashMap.values()) {
                System.out.println("- " + jd.GetJobName());
            }
        }
        return true;
    }

    // Edits job details
    public void EditJob(Scanner sc) {
        String JobName = StringValidation.ValidateString("Enter the job name to edit:", sc);

        if (!JobHashMap.containsKey(JobName)) {
            System.out.println("Job not found: " + JobName);
            return;
        }

        JobData jd = JobHashMap.get(JobName);

        // Display editable options
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Job Title");
        System.out.println("2. Company Name");
        System.out.println("3. Location");
        System.out.println("4. Description");
        System.out.println("5. Requirements");
        System.out.println("6. Cancel");

        int choice = IntValidation.ValidateInt("Enter your choice (1-6):", sc);

        switch (choice) {
            case 1: // Edit Job Title
                String newJobName = StringValidation.ValidateString("Enter the new job title:", sc);
                JobHashMap.remove(JobName); // Remove old entry
                jd = new JobData(newJobName, jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(newJobName, jd); // Insert updated job
                System.out.println("Job title updated successfully.");
                break;

            case 2: // Edit Company Name
                String newCompanyName = StringValidation.ValidateString("Enter the new company name:", sc);
                jd = new JobData(jd.GetJobName(), newCompanyName, jd.GetLocation(), jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Company name updated successfully.");
                break;

            case 3: // Edit Location
                String newLocation = StringValidation.ValidateString("Enter the new job location:", sc);
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), newLocation, jd.GetDescription(), jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Location updated successfully.");
                break;

            case 4: // Edit Description
                String newDescription = StringValidation.ValidateString("Enter the new job description:", sc);
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), newDescription, jd.GetRequirements());
                JobHashMap.put(jd.GetJobName(), jd);
                System.out.println("Description updated successfully.");
                break;

            case 5: // Edit Requirements
                List<String> newRequirements = new ArrayList<>();
                while (true) {
                    String requirement = StringValidation.ValidateString("Enter a new requirement (or type 'done' to finish):", sc);
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
        SaveDatabase();
    }
}

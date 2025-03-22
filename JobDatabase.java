import java.util.*;

public class JobDatabase {
    private final LinkedHashMap<String, JobData> JobHashMap;

    // add consts here
    private static final int EDIT_JOB_TITLE = 1;
    private static final int EDIT_COMPANY_NAME = 2;
    private static final int EDIT_LOCATION = 3;
    private static final int EDIT_JOB_DESCRIPTION = 4;
    private static final int EDIT_REQUIREMENTS = 5;

    // Initialize the database from the file
    public JobDatabase() {
        JobHashMap = JobDatabaseStorage.LoadJobDatabase();
    }

    // Save database to file
    public void SaveDatabase() {
        JobDatabaseStorage.SaveJobDatabase(JobHashMap);
    }

    // Adds a job role to the database by collecting all the needed infomation and putting it into the JobHashMap
    public void AddJob(Scanner sc, String PostedBy) {
        System.out.println("Welcome to the job addition page");

        String JobName = StringValidation.ValidateString("Please enter the job name: ", sc);


        String CompanyName = StringValidation.ValidateString("Please enter the company name: ", sc);


        String Location = StringValidation.ValidateString("Please enter the job location: ", sc);


        String  Description = StringValidation.ValidateString("Please enter the job description: ", sc);

        List<String> Requirements = new ArrayList<>();

        // loops and add entires to the list untill the user writes done
        do{
            String Requirement = StringValidation.ValidateString("Enter job requirement (type 'done' to finish):", sc);

            if (Requirement.equalsIgnoreCase("done"))
                Requirements.add(Requirement);

        }while (!Requirements.contains("done"));


        // Create and add the new job
        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements, PostedBy);
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

    public boolean ShowUserJobs(String Username) {
        boolean Found = false;

        System.out.println("\nJobs posted by " + Username + ":");
        for (JobData job : JobHashMap.values()) {
            if (job.GetPostedBy().equals(Username)) { // Check if job is posted by user
                System.out.println("- " + job.GetJobName());
                Found = true;
            }
        }

        if (!Found) {
            System.out.println("No jobs found for this user.");
        }
        return Found;
    }

    //shows the details of the selected job
    public void MoreDetails(Scanner sc) {

        String SelecetedJob = StringValidation.ValidateString("Enter the name of the string you want to see more detials of",sc);

        if (!JobHashMap.containsKey(SelecetedJob)) {
            System.out.println("Job not found: " + SelecetedJob);
            return;
        }


        JobData jd = JobHashMap.get(SelecetedJob);


        System.out.println("Job Name: " + jd.GetJobName());
        System.out.println("Company Name: " + jd.GetCompanyName());
        System.out.println("Location: " + jd.GetLocation());
        System.out.println("Description: " + jd.GetDescription());
        System.out.println("Requirements: " + jd.GetRequirements());
        System.out.println("PostedBy: " + jd.GetPostedBy());




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
            case EDIT_JOB_TITLE:
                String newJobName = StringValidation.ValidateString("Enter the new job title:", sc);

                //removes the old entry
                JobHashMap.remove(JobName);

                //inserts the updated job back in
                jd = new JobData(newJobName, jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), jd.GetRequirements(), jd.GetPostedBy());
                JobHashMap.put(newJobName, jd);

                //Displays a message to tell the user the output
                System.out.println("Job title updated successfully.");
                break;

            case EDIT_COMPANY_NAME:
                String newCompanyName = StringValidation.ValidateString("Enter the new company name:", sc);

                jd = new JobData(jd.GetJobName(), newCompanyName, jd.GetLocation(), jd.GetDescription(), jd.GetRequirements(), jd.GetPostedBy());
                JobHashMap.put(jd.GetJobName(), jd);

                System.out.println("Company name updated successfully.");
                break;

            case EDIT_LOCATION:
                String newLocation = StringValidation.ValidateString("Enter the new job location:", sc);

                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), newLocation, jd.GetDescription(), jd.GetRequirements(),jd.GetPostedBy());
                JobHashMap.put(jd.GetJobName(), jd);

                System.out.println("Location updated successfully.");
                break;

            case EDIT_JOB_DESCRIPTION:
                String newDescription = StringValidation.ValidateString("Enter the new job description:", sc);

                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), newDescription, jd.GetRequirements(),jd.GetPostedBy());
                JobHashMap.put(jd.GetJobName(), jd);

                System.out.println("Description updated successfully.");
                break;

            case EDIT_REQUIREMENTS:
                List<String> NewRequirements = new ArrayList<>();
                String Requirement;
                do{
                    Requirement = StringValidation.ValidateString("Enter a new requirement (or type 'done' to finish):", sc);
                    if (Requirement.equalsIgnoreCase("done")) break;
                    NewRequirements.add(Requirement);
                }while(Requirement != "done");

                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), NewRequirements,jd.GetPostedBy());
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

    public List<String> GetRequirementsFromJob(String JobName,Scanner sc) {
        do{
            if (!JobHashMap.containsKey(JobName)) {
                System.out.println("Job not found: " + JobName);
                return null;
            }

        }while (!JobHashMap.containsKey(JobName));

        JobData jd = JobHashMap.get(JobName);
        return jd.GetRequirements();

    }

    public void CheckJobApplication(String JobName,CVDatabase cvdb) {
        JobData jd = JobHashMap.get(JobName);
        NotificationSystem ns = new NotificationSystem();

        if (jd == null) {
            System.out.println("Job not found: " + JobName);
            return;
        }

        String Recruiter =jd.GetPostedBy();
        Boolean FoundCV = false;



        for(CVData cv :cvdb.CVHashMap.values()) {
            if (cv.GetJobName().equals(JobName)) {
                FoundCV = true;
                List<String> MatchedRequirements = new ArrayList<>(jd.GetRequirements());


                if (cv.GetMatchedRequirements() != null) {
                    MatchedRequirements.retainAll(cv.GetMatchedRequirements());
                } else {
                    MatchedRequirements.clear(); // Ensures it's an empty list if no matches
                }


                System.out.println("Applicant: " + cv.GetUsername());
                if (MatchedRequirements.isEmpty()) {
                    System.out.println("You matched none of the requirements.");
                } else {
                    System.out.println("Matched Requirements: " + MatchedRequirements);
                }


                ns.NotifyRecruiter(Recruiter, "A new CV was submitted for " + JobName + " by " + cv.GetUsername() +
                        ". Matched requirements: " + (MatchedRequirements.isEmpty() ? "None" : MatchedRequirements));
            }
        }


        if (!FoundCV) {
            System.out.println("no CVs have been submitted fo this job ");
        }

    }

    public Boolean FindJob(String JobName) {

        if (JobHashMap.containsKey(JobName)) {
            return true;
        }
        else{
            return false;
        }
    }


}

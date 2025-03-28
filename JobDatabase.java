import java.util.*;

public class JobDatabase {
    private final LinkedHashMap<String, JobData> JobHashMap;

    // add consts here
    private static final int EDIT_JOB_TITLE = 1;
    private static final int EDIT_COMPANY_NAME = 2;
    private static final int EDIT_LOCATION = 3;
    private static final int EDIT_JOB_DESCRIPTION = 4;
    private static final int EDIT_REQUIREMENTS = 5;
    private static final int EDIT_CANCELLED = 6;

    // Initialize the database from the file
    public JobDatabase() {
        JobHashMap = JobDatabaseStorage.LoadJobDatabase();
    }

    // Save database to file
    public void SaveDatabase() {
        JobDatabaseStorage.SaveJobDatabase(JobHashMap);
    }

    public Collection<JobData> GetJobs() {
        return JobHashMap.values();  // Returns all the jobs as a collection
    }

    // Adds a job role to the database by collecting all the needed information and putting it into the JobHashMap
    public void AddJob(Scanner sc, String PostedBy) {
        System.out.println("Welcome to the job addition page");
        String OverwriteChoice = "";
        String JobName;
        String CompanyName;
        String JobID;
        do {
            JobName = StringValidation.ValidateString("Please enter the job name: ", sc);
            CompanyName = StringValidation.ValidateString("Please enter the company name: ", sc);
            JobID = JobName + "_" + CompanyName;

            do{
                if (JobHashMap.containsKey(JobID)) {
                    OverwriteChoice = StringValidation.ValidateString("Warning: this job already exist to do wish to overwrite it? (y/n)", sc).trim().toLowerCase();

                    if (OverwriteChoice.equals("y")) {
                        System.out.println("Job will be overwritten");
                    } else if (OverwriteChoice.equals("n")) {
                        System.out.println("redirecting to change job name and job company name");
                    } else {
                        System.out.println("ERROR: Must be  either 'y' or 'n'");
                    }
                }
                else{
                    OverwriteChoice = "y";
                }

            }while(!OverwriteChoice.equals("y") || !OverwriteChoice.equals("n"));

        }while(OverwriteChoice.equals("y"));


        String Location = StringValidation.ValidateString("Please enter the job location: ", sc);
        String Description = StringValidation.ValidateString("Please enter the job description: ", sc);

        List<String> Requirements = new ArrayList<>();
        String Requirement;

        do {
            Requirement = StringValidation.ValidateString("Enter job requirement (type 'done' to finish):", sc);
            if (!Requirement.equalsIgnoreCase("done")) {
                Requirements.add(Requirement);
            }
        } while (!Requirement.equalsIgnoreCase("done"));

        JobData NewJob = new JobData(JobName, CompanyName, Location, Description, Requirements, PostedBy, JobID);
        JobHashMap.put(JobID, NewJob);
        System.out.println("Job added: " + JobName);
        SaveDatabase();

        RecruiterDashboard.ShowRecruiterDashboard(PostedBy, sc);
    }

    public void DeleteJob(Scanner sc, String Username) {
        String DeletedJob;

        // To store the job ID if found
        String JobIdToDelete = null;

        do{
            DeletedJob = StringValidation.ValidateString("Please enter the job name: ", sc);

            // Iterate through the job database to find the matching job
            for (Map.Entry<String, JobData> entry : JobHashMap.entrySet()) {
                JobData job = entry.getValue();
                if (job.GetJobName().equalsIgnoreCase(DeletedJob) && job.GetPostedBy().equals(Username)) {
                    // Store the Job ID
                    JobIdToDelete = entry.getKey();
                    break;
                }
            }

            // Check if the job was found and delete it
            if (JobIdToDelete != null) {
                JobHashMap.remove(JobIdToDelete);
                System.out.println("Job deleted successfully: " + DeletedJob);
                SaveDatabase();
                RecruiterDashboard.ShowRecruiterDashboard(Username, sc);
            } else {
                System.out.println("Job not found or you do not have permission to delete it.");
            }
        }while (JobIdToDelete == null);

    }

    public void EditJob(Scanner sc, String Username) {
        String JobIdToEdit;
        String JobNameToEdit;

        do {
            JobNameToEdit = StringValidation.ValidateString("Please enter the job Name: ", sc);
            JobIdToEdit = null; // Reset the JobId for each loop iteration

            // Iterate through the job database to find the matching job
            for (Map.Entry<String, JobData> entry : JobHashMap.entrySet()) {
                JobData job = entry.getValue();
                if (job.GetJobName().equalsIgnoreCase(JobNameToEdit) && job.GetPostedBy().equals(Username)) {
                    JobIdToEdit = entry.getKey(); // Store the Job ID
                    break; // Exit loop once a job is found
                }
            }

            if (JobIdToEdit == null) {
                System.out.println("ERROR: Job not found or you do not have permission to edit it.");
            }

        } while (JobIdToEdit == null);

        // shows them the options
        JobData jd = JobHashMap.get(JobIdToEdit);
        System.out.println("\nWhat would you like to edit?");
        System.out.println("1. Job Title");
        System.out.println("2. Company Name");
        System.out.println("3. Location");
        System.out.println("4. Description");
        System.out.println("5. Requirements");
        System.out.println("6. Cancel");

        int Choice = IntValidation.ValidateInt("Enter your choice (1-6):", sc);

        switch (Choice) {
            case EDIT_JOB_TITLE:
                String NewJobName = StringValidation.ValidateString("Enter the new job title:", sc);
                String NewJobID = NewJobName + "_" + jd.GetCompanyName();
                JobHashMap.remove(JobIdToEdit);
                jd = new JobData(NewJobName, jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), jd.GetRequirements(), jd.GetPostedBy(), NewJobID);
                JobHashMap.put(NewJobID, jd);
                System.out.println("Job title updated successfully.");
                break;

            case EDIT_COMPANY_NAME:
                String NewCompanyName = StringValidation.ValidateString("Enter the new company name:", sc);
                NewJobID = jd.GetJobID() + "_" + NewCompanyName;
                jd = new JobData(jd.GetJobName(), NewCompanyName, jd.GetLocation(), jd.GetDescription(), jd.GetRequirements(), jd.GetPostedBy(), NewJobID);
                JobHashMap.put(JobIdToEdit, jd);
                System.out.println("Company name updated successfully.");
                break;

            case EDIT_LOCATION:
                String NewLocation = StringValidation.ValidateString("Enter the new job location:", sc);
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), NewLocation, jd.GetDescription(), jd.GetRequirements(), jd.GetPostedBy(), JobIdToEdit);
                JobHashMap.put(JobIdToEdit, jd);
                System.out.println("Location updated successfully.");
                break;

            case EDIT_JOB_DESCRIPTION:
                String NewDescription = StringValidation.ValidateString("Enter the new job description:", sc);
                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), NewDescription, jd.GetRequirements(), jd.GetPostedBy(), JobIdToEdit);
                JobHashMap.put(JobIdToEdit, jd);
                System.out.println("Description updated successfully.");
                break;

            case EDIT_REQUIREMENTS:
                List<String> NewRequirements = new ArrayList<>();
                String Requirement;
                do {
                    Requirement = StringValidation.ValidateString("Enter a new requirement (or type 'done' to finish):", sc);

                    if (!Requirement.equalsIgnoreCase("done")) {
                        NewRequirements.add(Requirement);
                    }
                } while (!Requirement.equalsIgnoreCase("done"));

                jd = new JobData(jd.GetJobName(), jd.GetCompanyName(), jd.GetLocation(), jd.GetDescription(), NewRequirements, jd.GetPostedBy(), JobIdToEdit);
                JobHashMap.put(JobIdToEdit, jd);
                System.out.println("Requirements updated successfully.");
                break;

            case EDIT_CANCELLED:
                System.out.println("Edit cancelled.");
                RecruiterDashboard.ShowRecruiterDashboard(Username, sc);
                break;

            default:
                System.out.println("Invalid choice.");
                break;
        }
        SaveDatabase();
        RecruiterDashboard.ShowRecruiterDashboard(Username, sc);
    }

    public List<String> GetRequirementsFromJob(String JobId) {
        JobData jd = JobHashMap.get(JobId);
        return jd.GetRequirements();
    }

    public boolean ShowUserJobs(String Username) {
        boolean Found = false;

        System.out.println("\nJobs posted by " + Username + ":");
        for (JobData job : JobHashMap.values()) {
            // Check if job is posted by user
            if (job.GetPostedBy().equals(Username)) {
                System.out.println("- " + job.GetJobName());
                Found = true;
            }
        }

        if (!Found) {
            System.out.println("No jobs found for this user.");
        }
        return Found;
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

    //shows the details of the selected job
    public void MoreDetails(String Username, Scanner sc) {
        String selectedJob;
        JobData FoundJob = null;

        do {
            selectedJob = StringValidation.ValidateString("Enter the job name you want to see more details of:", sc);

            for (JobData job : JobHashMap.values()) {
                if (job.GetJobName().equalsIgnoreCase(selectedJob)) {
                    FoundJob = job;
                    break;
                }
            }

            if (FoundJob == null) {
                System.out.println("Job not found: " + selectedJob);
            }

        } while (FoundJob == null);

        // Display job details
        System.out.println("Job Name: " + FoundJob.GetJobName());
        System.out.println("Company Name: " + FoundJob.GetCompanyName());
        System.out.println("Location: " + FoundJob.GetLocation());
        System.out.println("Description: " + FoundJob.GetDescription());
        System.out.println("Requirements: " + FoundJob.GetRequirements());
        System.out.println("Posted By: " + FoundJob.GetPostedBy());

        ApplicantDashboard.ShowApplicantDashboard(Username, sc);
    }

    public void CheckJobApplication(String JobName,CVDatabase cvdb) {
        String JobId = "";

        // Iterate through the job database to find the matching job
        for (Map.Entry<String, JobData> entry : JobHashMap.entrySet()) {
            JobData job = entry.getValue();
            if (job.GetJobName().equalsIgnoreCase(JobName)) { // Compare with JobName
                // Store the Job ID
                JobId = entry.getKey();
                break;
            }
        }

        JobData jd = JobHashMap.get(JobId);
        NotificationSystem ns = new NotificationSystem();

        if (jd == null) {
            System.out.println("Job not found: " + JobName);
            return;
        }

        String Recruiter = jd.GetPostedBy();

        for (CVData cv : cvdb.CVHashMap.values()) {
            if (cv.GetJobName().equals(JobName)) {
                List<String> MatchedRequirements = new ArrayList<>(jd.GetRequirements());
                if (cv.GetMatchedRequirements() != null) {
                    MatchedRequirements.retainAll(cv.GetMatchedRequirements());
                } else {
                    // Ensures it's an empty list if no matches
                    MatchedRequirements.clear();
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
    }

    public void DisplayApplicantsCV(String Username, Scanner sc) {

        CVDatabase cvdb = new CVDatabase();
        JobDatabase jdb = new JobDatabase();


        String JobId = "";
        boolean FoundCV = false;
        String JobName;


        do{
            JobName=StringValidation.ValidateString("Please enter the job name you wish to see all applicants who applied their CV to:", sc);

            // Iterate through the job database to find the matching job
            for (Map.Entry<String, JobData> entry : JobHashMap.entrySet()) {
                JobData job = entry.getValue();
                if (job.GetJobName().equalsIgnoreCase(JobName) && job.GetPostedBy().equals(Username)) {
                    // Store the Job ID
                    JobId = entry.getKey();
                    break;
                }
            }


            // Put them back in the loop
            if (JobId == null) {
                System.out.println("Job not found: " + JobName);
            }

        }while(JobId.isEmpty());

        for (CVData cv : cvdb.CVHashMap.values()) {
            // Ensure the CV is for the correct job and is posted by the recruiter of the job
            if (cv.GetJobName().equalsIgnoreCase(JobName) && cv.GetPostedBy().equalsIgnoreCase(Username)) {

                List<String> MatchedRequirements = cv.GetMatchedRequirements();
                List<String> JobRequirements = jdb.GetRequirementsFromJob(JobId);


                //edge casing
                if (JobRequirements.isEmpty() || JobRequirements == null) {
                    System.out.println("ERROR: getting job requirements" + JobName);
                    return;
                }

                float Rating = CVRating.CvRateCalculations(MatchedRequirements, JobRequirements);

                // Display the applicant's CV details and rating
                System.out.println("Applicant: " + cv.GetUsername());
                System.out.println("Matched Requirements: " + MatchedRequirements);
                System.out.println("Rating: " + Rating + "%\n\n");

            }
        }

        // If no matching CVs were found, print a message
        if (!FoundCV) {
            System.out.println("No CVs uploaded for this job yet.");
            RecruiterDashboard.ShowRecruiterDashboard(Username, sc);
        }


        RecruiterDashboard.ShowRecruiterDashboard(Username, sc);


    }

}

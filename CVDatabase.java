import java.util.*;

public class CVDatabase {
    //initalises the linkedhashmap
    final LinkedHashMap<String, CVData> CVHashMap;

    // Load CV database from file (if exists)
    public CVDatabase() {
        CVHashMap = CVDatabaseStorage.LoadCVDatabase();
        System.out.println("CV Database Loaded");
    }

    // Save CV database to file
    public void SaveDatabase() {
        CVDatabaseStorage.SaveCVDatabase(CVHashMap);
    }

    // uploads the cv
    public void UploadCV(String Username, Scanner sc) {
        NLPProcessor nlp = new NLPProcessor();
        JobDatabase jdb = new JobDatabase();
        CVDatabase cvdb = new CVDatabase();

        boolean FoundJob;
        String JobName;
        String SelectedJobId = "";
        String PostedBy = "";



        // Loop until a valid job name is entered
        do {
            jdb.ShowAllJobs();  // Display available jobs
            JobName = StringValidation.ValidateString("Enter the name of the job you wish to submit your CV to:", sc);

            // Reset FoundJob to false for each iteration
            FoundJob = false;

            // Check if job exists by name
            for (JobData job : jdb.GetJobs()) {
                if (job.GetJobName().equalsIgnoreCase(JobName)) {
                    // Mark job as found
                    FoundJob = true;
                    // Stop once the job is found
                    break;
                }
            }

            if (!FoundJob) {
                System.out.println("Job not found. Please try again.");
            }
            // Repeat if job is not found
        } while (!FoundJob);


        // Now we know the job exists. Let's get the job key (ID)
        for (JobData job : jdb.GetJobs()) {
            if (job.GetJobName().equalsIgnoreCase(JobName)) {
                // Store the Job ID (key)
                SelectedJobId = job.GetJobID();
                PostedBy = job.GetPostedBy();
                break;
            }
        }

        List<String> Requirements = jdb.GetRequirementsFromJob(SelectedJobId);
        
        System.out.println("Please enter/paste your CV (press Enter twice to finish):");

        // used String Builder to keep it efficent and running quickly without creating uneeded objects
        StringBuilder CVContent = new StringBuilder();
        boolean lastLineEmpty = false;

        // reads line by line of scanner and then if two empty lines was submitted then the loop will end
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
                if (lastLineEmpty) {
                    break;
                }
                lastLineEmpty = true;
            } else {
                lastLineEmpty = false;
                CVContent.append(line).append("\n");
            }
        }

        // fianlly converts the StringBuilder back into a string then calls AnalyseAndMatch to show the the nlp to compare and show the matched requirements
        List<String> Matched = nlp.AnalyzeAndMatch(String.valueOf(CVContent), Requirements);

        //displays matched requirements
        System.out.println("Matched Requirements: " + Matched);

        float Rating = CVRating.CvRateCalculations(Matched,Requirements);
        System.out.println("Your Rating: " + Rating + "%");

        // Store the CV
        CVData NewCV = new CVData(Username,JobName,Matched,PostedBy,Rating);

        //puts it in the cvhashmap
        CVHashMap.put(Username, NewCV);
        System.out.println("CV uploaded successfully!");

        //saves this in the hashmap
        SaveDatabase();

        //now sends a notifcation to the recruiter when the database is checked later on
        jdb.CheckJobApplication(JobName,cvdb);

        // returns to the dashboard
        ApplicantDashboard.ShowApplicantDashboard(Username,sc);
    }

}


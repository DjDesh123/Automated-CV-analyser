import javax.print.attribute.standard.JobName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

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

    // this will be replaced as we are going to get the actual files and then rip it into this code via a folder or something
    public void UploadCV(String Username, Scanner sc) {
        NLPProcessor nlp = new NLPProcessor();
        JobDatabase jdb = new JobDatabase();
        CVDatabase cvdb = new CVDatabase();

        boolean FoundJob;
        String JobName;

        // loops till its correct
        do {
            // shows all the avaliable jobs
            jdb.ShowAllJobs();

            // gets the job name
            JobName = StringValidation.ValidateString("Enter the name of the job you wish to submit your cv to:", sc);

            FoundJob = jdb.FindJob(JobName);
        } while (!FoundJob);

        System.out.println("Exited the loop. Moving forward...");

        // this was used to asks user to select a job then i call the GetRequirements to get the requirements
        List<String> Requirements = jdb.GetRequirementsFromJob(JobName);


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

        // Store the CV
        CVData NewCV = new CVData(Username, CVContent.toString().trim(),JobName,Matched);

        //puts it in the cvhashmap
        CVHashMap.put(Username, NewCV);
        System.out.println("CV uploaded successfully!");

        //saves this in the hashmap
        SaveDatabase();


        //now sends a notifcation to the recruiter when the database is checked later on
        jdb.CheckJobApplication(JobName,cvdb);


        ApplicantDashboard.ShowApplicantDashboard(Username,sc);
    }

}


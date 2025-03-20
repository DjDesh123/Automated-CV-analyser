import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class CVDatabase {
    //initalises the linkedhashmap
    private final LinkedHashMap<String, CVData> CVHashMap;

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
        JobDatabase  jdb = new JobDatabase();

        jdb.ShowAllJobs();

        List<String> Requirements = jdb.GetRequirementsFromJob(sc);

        System.out.println("Please enter/paste your CV (press Enter twice to finish):");


        StringBuilder CVContent = new StringBuilder();
        boolean lastLineEmpty = false;

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

        List<String> Matched = nlp.AnalyzeAndMatch(String.valueOf(CVContent),Requirements);
        System.out.println("Matched Requirements: " + Matched);

        // Store the CV
        CVData NewCV = new CVData(Username, CVContent.toString().trim());
        CVHashMap.put(Username, NewCV);
        System.out.println("CV uploaded successfully!");

        SaveDatabase();
    }

    // Display a user's CV (for debugging/testing)
    public void ShowCV(String userName) {
        if (CVHashMap.containsKey(userName)) {
            System.out.println("\n" + userName + "'s CV:");
            System.out.println(CVHashMap.get(userName).GetCVContent());
        } else {
            System.out.println("No CV found for " + userName);
        }
    }
}


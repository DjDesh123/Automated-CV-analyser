import java.util.LinkedHashMap;
import java.util.Scanner;

public class CVDatabase {
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

    // Upload a CV
    public void UploadCV(String userName, Scanner sc) {
        System.out.println("Please enter/paste your CV (press Enter twice to finish):");

        StringBuilder cvContent = new StringBuilder();
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
                cvContent.append(line).append("\n");
            }
        }

        // Store the CV
        CVData newCV = new CVData(userName, cvContent.toString().trim());
        CVHashMap.put(userName, newCV);
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


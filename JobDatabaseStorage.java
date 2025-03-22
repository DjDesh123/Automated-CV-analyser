import java.io.*;
import java.util.LinkedHashMap;

public class JobDatabaseStorage {

    static final String SAVED_JOB_DATABASE_FILE = "SavedJobs.dat";


    public static void SaveJobDatabase(LinkedHashMap<String, JobData>  JobHashMap) {

        //uses try and catch to prevent the program from just crahsing if it goes wrong
        // this is used to try to open a file to save data
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVED_JOB_DATABASE_FILE))) {

            // Serializes and writes LinkedHashMap to file
            out.writeObject(JobHashMap);

            //displays a message to say if it was sucessful
            System.out.println("Database saved successfully at: " + new File(SAVED_JOB_DATABASE_FILE).getAbsolutePath());
        } catch (IOException e) {
            // if it was not successful then gives a message
            //e.getMessage basically just says directly what's the issue than just saying "there was an issue"
            System.err.println("Error saving database: " + e.getMessage());
        }
    }


    //Loads the user database from a file.
    public static LinkedHashMap<String, JobData>  LoadJobDatabase() {
        // trys to open the file and tries to read the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVED_JOB_DATABASE_FILE))) {
            //in short .dat stores by using bytes and we are passing an object so we need to turn it back into an object
            LinkedHashMap<String, JobData> LoadedData = (LinkedHashMap<String, JobData>) in.readObject();
            // A message to display that the database was loaded and also the amount of users using LoadedData.size()
            System.out.println("Database loaded with " + LoadedData.size() + " jobs");
            //returns LoadData so its back to the program and not just in LoadDatabase
            return LoadedData;

            //incase the file is not found
        } catch (FileNotFoundException e) {
            //creates a new one if it's not found
            System.out.println("No database file found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
            //this willjust display the error if the database doesnt load
            System.err.println("Error loading database: " + e.getMessage());
        }
        // If the file doesn't exist, return an empty database
        return new LinkedHashMap<>();
    }
}


















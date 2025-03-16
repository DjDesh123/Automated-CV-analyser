
import java.io.*;
import java.util.LinkedHashMap;

// this is class responsible for the saving and the loading of the database from the final
public class DatabaseStorage {
    // The file where data is stored
    static final String DATABASE_FILE_NAME = "SavedDatabase.dat";


    // what i can add here is maybe like some temp data for the database so i can show that it also has some auto log in and stuff like that

    // Saves the user database to a file
    // we have LinkedHashMap inside the parameters to access all the data not the actual database
    public static void SaveDatabase(LinkedHashMap<String, UserInfo> userCredentialMap) {
        System.out.println("🔹 Attempting to save database...");

        //uses try and catch to prevent the program from just crahsing if it gOES WRONG
        // this is used to try to open a file to save data
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE_NAME))) {

            // Serializes and writes LinkedHashMap to file
            out.writeObject(userCredentialMap);

            //displays a message to say if it was sucessful
            System.out.println("Database saved successfully at: " + new File(DATABASE_FILE_NAME).getAbsolutePath());
        } catch (IOException e) {
            // if it was not successful then gives a message
            //e.getMessage basically just says directly whats the issue than just saying "there was an issue"
            System.err.println("Error saving database: " + e.getMessage());
        }
    }

    //Loads the user database from a file.
    public static LinkedHashMap<String, UserInfo> LoadDatabase() {
        // to inform that we are trying to laod the database
        System.out.println("Attempting to load database...");

        // trys to open the file and tries to wread the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATABASE_FILE_NAME))) {
            //in.readObject(); reads the file however it doesnt know what object its reading bc its only reading bytes so
            //(LinkedHashMap<String, UserInfo>) in.readObject(); with this we manually tell it that its a linkedhashmap with whats inside
            // then its just sotred in LoadedData
            LinkedHashMap<String, UserInfo> LoadedData = (LinkedHashMap<String, UserInfo>) in.readObject();
            // A message to display that the database was loaded and also the amount of users using LoadedData.size()
            System.out.println("Database loaded with " + LoadedData.size() + " users.");
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

// make back up json file
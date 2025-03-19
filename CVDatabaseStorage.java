import java.io.*;
import java.util.LinkedHashMap;

public class CVDatabaseStorage {
    private static final String FILE_NAME = "CVDatabase.dat";

    public static void SaveCVDatabase(LinkedHashMap<String, CVData> CVHashMap) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(CVHashMap);
        } catch (IOException e) {
            System.err.println("Error saving CV database: " + e.getMessage());
        }
    }

    public static LinkedHashMap<String, CVData> LoadCVDatabase() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (LinkedHashMap<String, CVData>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new LinkedHashMap<>(); // Return empty database if file doesn't exist
        }
    }
}
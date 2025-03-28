import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;

public class NotificationSystemStorage {
    private static final String FILE_NAME = "NotificationSystemStorage.dat";

    // Save notifications to file
    public static void SaveNotifications(LinkedHashMap<String, List<String>> RecruiterNotifications) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(RecruiterNotifications);
            System.out.println("Notifications saved successfully (" + RecruiterNotifications.size() + " recruiters).");
        } catch (IOException e) {
            System.err.println("Error saving notifications: " + e.getMessage());
        }
    }

    // Load notifications from file
    public static LinkedHashMap<String, List<String>> LoadNotifications() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            LinkedHashMap<String, List<String>> LoadedData = (LinkedHashMap<String, List<String>>) in.readObject();
            return LoadedData;
        } catch (FileNotFoundException e) {
            System.out.println("No notification file found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading notifications: " + e.getMessage());
        }
        // Return empty if file doesn't exist
        return new LinkedHashMap<>();
    }
}

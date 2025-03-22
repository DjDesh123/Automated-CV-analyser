import java.util.*;

public class NotificationSystem {
    private LinkedHashMap<String, List<String>> RecruiterNotifications;

    // Constructor: Load notifications from file
    public NotificationSystem() {
        RecruiterNotifications = NotificationSystemStorage.LoadNotifications();
    }

    // Save notifications to file
    public void SaveDatabase() {
        NotificationSystemStorage.SaveNotifications(RecruiterNotifications);
    }

    // Store a notification for a recruiter
    public void NotifyRecruiter(String RecruiterUsername, String Message) {
        RecruiterNotifications.putIfAbsent(RecruiterUsername, new ArrayList<>());
        RecruiterNotifications.get(RecruiterUsername).add(Message);
        System.out.println("Notification sent to " + RecruiterUsername + ": " + Message);

        //saves it after a new notification is created
        SaveDatabase();
    }

    // Display notifications for a recruiter
    public void ShowNotification(String RecruiterUsername) {
        List<String> Notifications = RecruiterNotifications.getOrDefault(RecruiterUsername, new ArrayList<>());

        if (Notifications.isEmpty()) {
            System.out.println("No notifications found for " + RecruiterUsername);
        } else {
            System.out.println("Notifications for " + RecruiterUsername + ":");
            for (String Notification : Notifications) {
                System.out.println("- " + Notification);
            }
            // Clear notifications after showing them
            RecruiterNotifications.get(RecruiterUsername).clear();
            // Saves it after clearing
            SaveDatabase();
        }
    }
}

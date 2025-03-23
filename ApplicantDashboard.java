import java.util.Scanner;

public class ApplicantDashboard {
    private static final int UPLOAD_CV =  1;
    private static final int MORE_DETAILS =  2;
    private static final int MAIN_MENU =  3;


    public static void ShowApplicantDashboard(String Username, Scanner sc) {
        System.out.println("Applicant Dashboard");
        JobDatabase jdb = new JobDatabase();
        MenuSchedular ms = new MenuSchedular(sc);
        CVDatabase cvdb = new CVDatabase();


        // shows all jobs and if the there's no jobs present then it will send them back to the main menu
        if (!jdb.ShowAllJobs()) {
            System.out.println("redirecting to main menu .....");
            ms.DisplayMenu();
        }

        int MenuChoice;

        do {
            System.out.println("1. Post your cv");
            System.out.println("2. More details");
            System.out.println("3. Exit");
            // allows the user to navigate the options
            MenuChoice = IntValidation.ValidateInt("Enter your choice (1-3)", sc);

            switch (MenuChoice) {
                case UPLOAD_CV:
                    cvdb.UploadCV(Username, sc);
                    break;
                case MORE_DETAILS:
                    jdb.MoreDetails(Username,sc);
                    break;
                case MAIN_MENU:
                    ms.DisplayMenu();
                    break;
                default:
                    System.out.println("Invalid choice");

            }
        } while (MenuChoice < UPLOAD_CV || MenuChoice > MAIN_MENU);
    }
}



import java.rmi.activation.ActivationGroup_Stub;
import java.util.Scanner;

public class ApplicantDashboard {
    private static final int UPLOAD_CV =  1;
    private static final int MORE_DETAILS =  2;
    private static final int DISPLAYS_MENU =  3;


    public static void ShowApplicantDashboard(String Username){
        System.out.println("Applicant Dashboard");
        Scanner sc = new Scanner(System.in);
        JobDatabase jdb = new JobDatabase();
        MenuSchedular ms = new MenuSchedular(sc);
        CVDatabase cvdb = new CVDatabase();



        // shows all jobs and if the there's no jobs present then it will send them back to the main menu
        if(!jdb.ShowAllJobs()){
            System.out.println("redirecting to main menu .....");
            ms.DisplayMenu();
        }

    while (true) {
        // allows the user to navigate the options
        int MenuChoice = IntValidation.ValidateInt("Do you wish to post your cv to a job, see more details or Exit to main menu (1-3)", sc);

        switch (MenuChoice) {
            case UPLOAD_CV:
                cvdb.UploadCV(Username, sc);
                break;
            case MORE_DETAILS:
                jdb.MoreDetails(sc);
                break;
            case DISPLAYS_MENU:
                ms.DisplayMenu();
                break;
            default:
                System.out.println("Invalid choice");

        }
    }



    }





}
    /* for this section be able to
     - to send it back to the applicant to be able to see who wants the job
     */


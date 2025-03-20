import java.rmi.activation.ActivationGroup_Stub;
import java.util.Scanner;

public class ApplicantDashboard {
    public static void ShowApplicantDashboard(String Username){
        System.out.println("Applicant Dashboard");
        Scanner sc = new Scanner(System.in);
        JobDatabase jdb = new JobDatabase();
        MenuSchedular ms = new MenuSchedular(sc);
        CVDatabase cvdb = new CVDatabase();

        // shows all jobs and if the theres no jobs present then it will send them back to the main menu
        if(!jdb.ShowAllJobs()){
            System.out.println("redirecting to main menu .....");
            ms.DisplayMenu();
        }

    while (true) {

        int MenuChoice = IntValidation.ValidateInt("Do you wish to post your cv to a job, see more details or Exit to main menu (1-3)", sc);

        switch (MenuChoice) {
            case 1:
                cvdb.UploadCV(Username, sc);
                break;
            case 2:
                jdb.MoreDetails(sc);
                break;
            case 3:
                ms.DisplayMenu();
                break;
            default:
                System.out.println("Invalid choice");

        }
    }



    }





}
    /* for this section be able to
     - see all available job postings
     - allow the user to post their cv
     - for it to be analysed by the nlp
     - the nlp to use the requirements as the searching tool
     - a saving mechanism for jobs they applied for
     - to send it back to the applicant to be able to see who wants the job
     - and to show which requirement the user hit
     */


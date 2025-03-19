import java.rmi.activation.ActivationGroup_Stub;
import java.util.Scanner;

public class ApplicantDashboard {
    public static void ShowApplicantDashboard(){
        System.out.println("Applicant Dashboard");
        Scanner sc = new Scanner(System.in);
        JobDatabase jdb = new JobDatabase();
        MenuSchedular ms = new MenuSchedular(sc);


        if(!jdb.ShowAllJobs()){
            System.out.println("redirecting to main menu .....");
            ms.DisplayMenu();
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


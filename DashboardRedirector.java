import java.util.Scanner;

public class DashboardRedirector {

    public static void Redrict(String Username, Scanner sc,LogInDatabase db) {

        // gets the AccountType of the user
        UserInfo User = db.GetUser(Username);
        String AccountType = User.GetAccountType();

        // if the AccountType matches either recruiter or applicant then its redirected to the correct dashboard
        if (AccountType.equals("Recruiter")) {
            //sends him to the RecruiterDashboard.
            RecruiterDashboard.ShowRecruiterDashboard(Username,sc);
        }
        else if (AccountType.equals("Applicant")) {
            ApplicantDashboard.ShowApplicantDashboard(Username,sc);
        }

    }

}

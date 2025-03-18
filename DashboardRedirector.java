// What i want this class to do is basically take from my database and then go "oh they say they are an applicant/recuiter then sent to their repsect dashbaords "
public class DashboardRedirector {

    public static void Redrict(String AccountType ) {

        if (AccountType.equals("Recruiter")) {
            //sends him to the RecruiterDashboard.
            RecruiterDashboard.ShowRecruiterDashboard();
        }
        else if (AccountType.equals("Applicant")) {
            ApplicantDashboard.ShowApplicantDashboard();
        }
        else{
            System.out.println("Invalid Account Type");
        }










    }

}

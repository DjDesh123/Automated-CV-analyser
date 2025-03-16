import java.util.Arrays;

public class JobData {
    //initalising fields for the needed data for the database and job role
    private String JobName;
    private String CompanyName;
    private String Location;
    private String Description;
    private String[] Requirements;

    // a constructer to prevent from having to input each bit of data one by one
    public JobData(String JobName, String CompanyName, String Location, String Description, String[] Requirements) {
      this.JobName= JobName;
      this.CompanyName= CompanyName;
      this.Location= Location;
      this.Description= Description;
      this.Requirements= Requirements;
    }

    public String GetJobName() {
        return JobName;
    }

    public String GetCompanyName() {
        return CompanyName;
    }

    public String GetLocation() {
        return Location;
    }

    public String getDescription() {
        return Description;
    }

    public String[] getRequirements() {
        return Requirements;
    }


    // to display the infomation
    public String ToString(){
        return JobName  + " at " + CompanyName+ " in " + Location +
                "Description" +Description +"\n    Requirements: " + Arrays.toString(Requirements);
    }
}

import java.io.Serializable;
import java.util.List;

public class JobData implements Serializable {

    //to help with verison control
    private static final long SerialVersionUID = 1L;



    //initalising fields for the needed data for the database and job role
    private String JobName;
    private String CompanyName;
    private String Location;
    private String Description;
    private List<String> Requirements;
    private String PostedBy;

    // a constructer to prevent from having to input each bit of data one by one
    public JobData(String JobName, String CompanyName, String Location, String Description, List<String> Requirements, String PostedBy) {
      this.JobName= JobName;
      this.CompanyName= CompanyName;
      this.Location= Location;
      this.Description= Description;
      this.Requirements= Requirements;
      this.PostedBy = PostedBy;
    }

    // getter functions
    public String GetJobName() {
        return JobName;
    }

    public String GetCompanyName() {
        return CompanyName;
    }

    public String GetLocation() {
        return Location;
    }

    public String GetDescription() {
        return Description;
    }

    public List<String> GetRequirements() {
        return Requirements;
    }

    public String GetPostedBy() {return PostedBy;}


    // to display the infomation
    public String ToString(){
        return JobName  + " at " + CompanyName+ " in " + Location +
                "Description" +Description +"\n    Requirements: " + Requirements;
    }
}

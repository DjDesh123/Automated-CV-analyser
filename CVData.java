import java.io.Serializable;
import java.util.List;

public class CVData implements Serializable {

    private static final long serialVersionUID = 1L;

    public String Username;
    public String JobName;
    public List<String> MatchedRequirements;
    public String PostedBy;
    public float Rating;


    public CVData(String Username,String JobName, List<String> MatchedRequirements,String PostedBy,float Rating) {
        this.Username = Username;
        this.JobName = JobName;
        this.MatchedRequirements = MatchedRequirements;
        this.PostedBy = PostedBy;
        this.Rating=Rating;
    }

    // getter

    public String GetJobName() {return JobName;}

    public String GetUsername(){
        return Username;
    }

    public List<String> GetMatchedRequirements() {return MatchedRequirements;}

    public String GetPostedBy(){return PostedBy;}
}

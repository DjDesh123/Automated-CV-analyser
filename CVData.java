import java.io.Serializable;
import java.util.List;

public class CVData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String CVID;
    private String Username;
    private String JobName;
    private List<String> MatchedRequirements;
    private String PostedBy;
    private float Rating;


    public CVData(String CVID,String Username,String JobName, List<String> MatchedRequirements,String PostedBy,float Rating) {
        this.CVID = CVID;
        this.Username = Username;
        this.JobName = JobName;
        this.MatchedRequirements = MatchedRequirements;
        this.PostedBy = PostedBy;
        this.Rating=Rating;
    }

    // getter

    public String getCVID() {return CVID;}

    public String GetJobName() {return JobName;}

    public String GetUsername(){
        return Username;
    }

    public List<String> GetMatchedRequirements() {return MatchedRequirements;}

    public String GetPostedBy(){return PostedBy;}

    public float GetRating(){return Rating;}

}

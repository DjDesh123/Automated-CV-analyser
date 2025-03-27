import java.io.Serializable;
import java.util.List;

public class CVData implements Serializable {

    private static final long serialVersionUID = 1L;

    public String Username;
    public String CVContent;
    public String JobName;
    public List<String> MatchedRequirements;
    public String PostedBy;
    public float Rating;


    public CVData(String Username, String CVContent, String JobName, List<String> MatchedRequirements,String PostedBy,float Rating) {
        this.Username = Username;
        this.CVContent = CVContent;
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

    public String GetCVContent(){
        return CVContent;
    }

    public float GetRating(){return Rating;}

    public List<String> GetMatchedRequirements() {return MatchedRequirements;}

    public String GetPostedBy(){return PostedBy;}
}


// what we might do is logically if we can go on the user account and say ohh if the recrutier job name is selectd then we go through the cv database and see if we have a match to that jobId then we will display them and their matched requirements
// then we can add the ranking system which will be a simple dynamic formula which will count the requirements and then count the amount of requirements hit then get a percentage difference and thats the matched perecentage
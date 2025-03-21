import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class CVData implements Serializable {

    private static final long serialVersionUID = 1L;

    public String Username;
    public String CVContent;
    public String JobName;
    public List<String> MatchedRequirements;


    public CVData(String Username, String CVContent, String JobName, List<String> matched) {
        this.Username = Username;
        this.CVContent = CVContent;
        this.JobName = JobName;
        this.MatchedRequirements = MatchedRequirements;
    }

    // getter

    public String GetJobName() {return JobName;}

    public String GetUsername(){
        return Username;
    }

    public String GetCVContent(){
        return CVContent;
    }

    public List<String> GetMatchedRequirements() {return MatchedRequirements;}


}

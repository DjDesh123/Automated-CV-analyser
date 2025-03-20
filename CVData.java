import java.io.Serializable;
public class CVData implements Serializable {

    private static final long serialVersionUID = 1L;


    public String Username;
    public String CVContent;


    public CVData(String Username, String CVContent) {
        this.Username = Username;
        this.CVContent = CVContent;
    }

    // getter

    public String GetUsername(){
        return Username;
    }

    public String GetCVContent(){
        return CVContent;
    }

}

import java.io.Serializable;

//Make the class Serializable
//this just means it allows java to save Objects into a file as byte
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String Password;
    private String Salt;
    private String AccountType;

    // this is my constructer which will initalise the passowrd and account type
    public UserInfo(String Password, String AccountType,String Salt) {

        this.Password = Password;
        this.Salt = Salt;
        this.AccountType = AccountType;

    }


    // we have getters so our private variable can access the rest of the class without causing a big security risk
    public String GetPassword(){
        return Password;
    }

    public String GetSalt(){return Salt;}


    public String GetAccountType(){
        return AccountType;
    }




}

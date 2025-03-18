// decided to do LinkedhashMaps Because it allows me to be able to see what comes in and what order and keeps a level of organisation whilst sacrificing a little response time
// make this more deshawn code than ai so change things and also add notes to really understand whats going on
import java.util.LinkedHashMap;

// a class that will deal with the log in Linked Hashmap
public class LogInDatabase {


    // stores all the users in memeory
    private static LinkedHashMap<String, UserInfo> UserCredentialMap;

    // Constructor - Loads the database when this class is created
    public LogInDatabase() {
        UserCredentialMap = DatabaseStorage.LoadDatabase();
    }

    // a getter so i can get it and check the username before allowing the user to enter their password
    public LinkedHashMap<String, UserInfo> GetUserCredentialMap() {
        return UserCredentialMap;
    }
    // Saves the database to file
    public void SaveDatabase() {
        DatabaseStorage.SaveDatabase(UserCredentialMap);
    }


    // this will be used to add the username and passwords into the database for the sign-up page
    public void AddEntry(String Username, String Password, String AccountType) {
        PasswordHashing ph = new PasswordHashing();
        String Salt = ph.GetSalt();
        String HashedPassword = ph.HashingPassword(Password,Salt);

        // Print to verify hashing and salting
        System.out.println("Username: " + Username);
        System.out.println("Original Password: " + Password);
        System.out.println("Salt: " + Salt);
        System.out.println("Hashed Password: " + HashedPassword);
        System.out.println("----------------------");


        // puts the data on the linked hashmap
        UserCredentialMap.put(Username, new UserInfo(HashedPassword,AccountType,Salt));
        SaveDatabase();
    }


// add job needs to have all the details in and then i can just store it straight into the database

    public Boolean CheckIfFileExists() {
       if (UserCredentialMap.isEmpty()) {
           return true;

       }else {
           return false;
       }
    }



    // to check if the credentials are valid
    public boolean CheckUserCredentials(String Username, String Password) {
        // if we cant find the username in the database we just show an error message
        if (!UserCredentialMap.containsKey(Username)) {
            System.out.println("Error: Username not found!");
            return false;
        }

        // Retrieve stored user info
        UserInfo User = UserCredentialMap.get(Username);
        String StoredSalt = User.GetSalt();
        String StoredHashedPassword = User.GetPassword();

        // Hash input password using stored salt
        PasswordHashing ph = new PasswordHashing();
        String HashedInput = ph.HashingPassword(Password, StoredSalt);

        // Debugging prints
        System.out.println("\n[Login Attempt] Username: " + Username);
        System.out.println("Stored Hashed Password: " + StoredHashedPassword);
        System.out.println("Input Hashed Password: " + HashedInput);
        System.out.println("Stored Salt: " + StoredSalt);

        // Compare stored hash vs the input hash
        if (HashedInput.equals(StoredHashedPassword)) {
            System.out.println("Password Match! User authenticated.");
            return true;
        } else {
            System.out.println("Password Did Not Match! Authentication failed.");
            return false;
        }
    }

    public static UserInfo GetUser(String Username){
        return UserCredentialMap.get(Username);

    }

}

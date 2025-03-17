import java.io.File;
import java.util.Scanner;


// ideally tidy up the log in page and instead of trying to store an invalid option we will stop it there with a certain command
public class Main {
    public static void main(String[] args) {
        //loaded my database in first
        Scanner sc = new Scanner(System.in);
        LogInDatabase db = new LogInDatabase();
        MenuSchedular ms = new MenuSchedular(sc);

        // Check if database file exists
        //this should be in the databse file because its taking space
        File DatabaseFile = new File("SavedDatabase.dat");
        if (DatabaseFile.exists()) {
            System.out.println("Database file exists at: " + DatabaseFile.getAbsolutePath());
        } else {
            System.out.println("Database file does NOT exist. Creating a new one.");
        }



        ms.DisplayMenu();


    }


}

//make the data from the hashtable to read and also export in a file so it won't just erase each time.
//make add job requirements an array because thats a lot better to deal with and make a loop so i can loop how many requirements they want to add
// could write a for loop by just asking the user how many entries they want to add
// add a third option to end the program
//make an int validation checker so it will constantly check if the user entered a correct integer then the do while will check if its in range
// the file handling should be in the LogInDatabse not in main its better to encapsulate here
//save the database before ending


/*
Changes from the Class Diagram:

1. Renamed Classes to Make More Sense
   - JobData - Instead of JobPosting, shorter and clearer
   - LogInDatabase - Instead of UserDatabase, makes it clear it's just for login stuff
   - MenuSchedular - Instead of MainMenu, more accurate name
   - RecruiterDashboard - Added to keep recruiter features separate

2. Changed How Data is Stored
   - Used LinkedHashMap in JobDatabase and LogInDatabase instead of lists
   - Reason: Keeps things in order and makes searching faster
   - Made UserInfo Serializable so data can be saved to a file

3. Fixed How Jobs Are Added (AddJob)
   - AddingJob() was written to make entering job details easier
   - Planned Change: Turn Requirements into an array or list instead of one long string

4. Added Input Validation (StringValidation)
   - Makes sure usernames and passwords are only letters and numbers
   - Uses regex "[a-zA-Z0-9]{3,15}" to stop invalid input

5. Improved Password Security (PasswordHashing)
   - Instead of saving passwords as plain text, added:
     - Salting (random unique value for each password)
     - SHA-256 hashing (one-way encryption)
   - Reason: Makes passwords safer and stops brute-force attacks

6. Made Sure Data is Saved to a File
   - In Main.java, the program checks if a saved database file (SavedDatabase.dat) exists
   - If not, it creates a new one
   - Reason: So user and job data don’t get lost when the program is closed

7. Simplified Sign-Up (SignUpPage)
   - Used a ternary operator instead of a bunch of if-else statements:
     String AccountType = (AccountChoice == 1) ? "Recruiter" : "Applicant";
   - Reason: Less code, same result

8. Created RecruiterDashboard to Keep Things Organized
   - Recruiters now have their own page to manage job postings
   - Reason: The class diagram mixed everything together, which was messy

9. Added Error Handling (Try-Catch)
   - Plan to use try-catch blocks in AddJob to stop crashes from bad input
   - Reason: Makes the program run smoother and easier to debug

10. Removed Debugging Prints in LogInDatabase
   - Before, hashed passwords and salts were printed to the console
   - Reason: That’s a security risk, so it was taken out before final submission
*/

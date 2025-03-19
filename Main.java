import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuSchedular ms = new MenuSchedular(sc);


        ms.DisplayMenu();


    }


}



// could write a for loop by just asking the user how many entries they want to add



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

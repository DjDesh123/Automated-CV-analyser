import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuSchedular ms = new MenuSchedular(sc);


        ms.DisplayMenu();


    }


}


/* download these to run the program (its ran using intelj)
   https://nlp.stanford.edu/software/lex-parser.html#Download
   https://commons.apache.org/proper/commons-text/download_text.cgi
 */


/*
Changes from original plan

1. User class
 - named UserInfo as it makes more sense
 - only stores the username password salt and account type as they were the only needed information to be stored
 - login and register was made into separate classes to keep them modular and improve the readability

 2. Recruiter
 - changed hashmap to linkedHashMap because of the predictable iteration order as well as the easier to debug and do maintenance on
- added the user the option to AddJob EditJob and DeleteJob on the same page as the recruiter then direct them to the database as it ties more with the database than JobPosting
- There's getters and loops to get the job information and also to find the key via the job name

3. JobPosting
- did not seem like it was needed and put the methods in a more logical place

4. Database Manager
- as there are multiple databases with linked elements in them instead of one general manager to handle the lot the managers are in the database itself to handle the mechanism that the database needs to do
- did not need to clear the database and getAllCandidates is not needed either to be a separate method

5. CandidateRanker
- do not need a separate database to rank candidates

6. Setting Manager
- couldn't find a logical way of implementing it as in the report we wanted constant access to the setting button which is more difficult as it would have causes constant switch statements to be created
- could have been implemented in a gui sense but more difficult in a cli sense

7. Skill Matcher
- Skill matcher was turned into the AnalyseAndMatch method that goes and uses multiple features of the nlp and other libraries to match the terms correctly to the requirements
- don't see a need for "word importance" as in group conversations this was not mentioned
-content score was just the rating that is implemented in this code

8. Database
- instead of just one database there's multiple that deal with different data just linked by a common element so they can interact with each other more seamlessly
- insert and retrieve was implemented in a broad amount of methods than their own particular methods

9.CvRatingManager
- Did not need a Manager as I already had a class that generated and also put the selected rating of the cv in the CVDatabase which acts more as a holds all information about the cv than just the cv itself
- Did not need a separate database for CVRating

10. Applicant
- in the UploadCV that shows you the jobs that you can apply for instead of having to separate methods and add more details where oyu can see al the details about the job like you could have the ui representation from the prototype as an option.
- applied for jobs database is already covered in the cvDatabase and then the JobPosting database is created for all jobs till the recruiter decides to delete the job themselves

11. JobBrowser
- moved the method oof viewJobs and GetJobDetails to thE Respected class of JobDatabase that handles all necessary action that involve the database
- made a broad database with JobHashMap so then I would not need to have extra databases to deal with when it's not needed

12. Recruiter Helper
- was removed and added to the JobDatabase and that seemed more logical being placed there than a separate class
- there's different classes and databases to deal with the information that this class is using

13. Applicant Helper
- did not seem needed as this information is already accessed by the database themselves as It's where it stored
- view job was not needed in the applicant helper, but it's referenced in the Dashboard of the recruiter

14. CVUploader
- instead of having a dedicated class that deals with the updating and uploading of the cv I have method that does that for me which also will overwrite previous attempts as the key is the same so it does all feature efficiently whe applying for the same job by the same user whilst not effecting other jobs
- this means I don't need the method of replaceCV and also UpdateCV which then already changes the report as the report is effected via the data that is inputted

15. ThreadsManager
- I thought that this wasn't needed as of currently because it seems more useful to create threads when the NLP is more complex or the database is so large that its needed but as the program databases are fairly simple and can be changed constantly by deleting jobs I thought it was needed as of current

16. Encryption Manager
- all my encryption is dealt with in the PasswordHashing class and that all needed encrypted data is dealt there as we are only going to use Salting for the passwords to keep a level of security
- there's also comparison to handle encryption and decryption so separate methods didn't seem needed

18. JobWorker
- the jobs are processed via the database being loaded so the processing and the runTasks aren't needed as there's a way to make the database class do it themselves

19. QueueManager
- this was to store task in multithreading but as I am loading the cv database and job database through calling the class which runs fast enough so there did not see much benefit in threading at this current point of the project.If this project was a lot bigger than I can see a reason to MultiThread

20. Design Choices
- whilst the classes format has changed and how the code functions itself the program still follows the same design and rough layout that a cli format could follow. all the same functionality is there, and it still works the same way as planned in the presentation .
- there have been some creative tweaks around the code of new features and new knowledge that could elevate the project to new heights that I originally did not know existed at the time of the planning stage

 */
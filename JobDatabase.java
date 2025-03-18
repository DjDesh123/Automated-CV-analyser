import javax.print.attribute.standard.JobName;
import java.util.LinkedHashMap;

public class JobDatabase {

    private LinkedHashMap<String, JobData>  JobHashMap;

    // need to initalise the database
    public JobDatabase() {
        JobHashMap = new LinkedHashMap<>();
    }

    public void SaveDatabase() {
        JobDatabaseStorage.SaveJobDatabase(JobHashMap);
    }

    // this method adds a job role to our database.
    public void AddJob(JobData jd) {
        JobHashMap.put(jd.GetJobName(), jd);
        System.out.println("Job added: " + jd.GetJobName());
        SaveDatabase();
    }

    // this method is to delete a job off our database
    public void DeleteJob(JobData jd){
        if (JobHashMap.containsKey(jd.GetJobName())){
            JobHashMap.remove(jd.GetJobName());
            System.out.println("Job deleted successfully: " + jd.GetJobName());
        }
        else {
            System.out.println("Job not found: " + jd.GetJobName());
        }

    }
    // this will show all the job in the beginning in the program
    public boolean ShowAllJobs(){
        if (JobHashMap.isEmpty()){
            System.out.println("No jobs found");
            return false;
        }
        else {
            System.out.println("\nAll Jobs:");
            // LOOPS through the databse and displays all job names
            for (JobData jd : JobHashMap.values()) {
                System.out.println(jd.GetJobName());
            }
        }

        return true;
    }
}

//need to create a saving mechanism for this database

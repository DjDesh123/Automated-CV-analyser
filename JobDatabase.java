import java.util.LinkedHashMap;

public class JobDatabase {

    private LinkedHashMap<String, JobData>  JobHashMap;

    // need to initalise the database
    public JobDatabase() {
        JobHashMap = new LinkedHashMap<>();
    }

    // this method adds a job role to our database.
    public void AddJob(JobData jd){
        JobHashMap.put(jd.GetJobName(), jd);
        System.out.println("Job added: " + jd.GetJobName());
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

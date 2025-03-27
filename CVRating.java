import java.util.List;

public class CVRating {

    public CVRating(List<String> matched, List<String> requirements) {
    }

    // Method to calculate the match rating of a CV based on the matched requirements
    public static float CvRateCalculations(List<String> Matched, List<String> Requirements){
        // Count the number of matched requirements
        int matchedCount = Matched.size();

        // Calculate the percentage of matched requirements
        float MatchPercentage = ((float) matchedCount / Requirements.size()) * 100;

        return MatchPercentage;  // Return the match percentage
    }
}

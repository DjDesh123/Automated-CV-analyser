import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.util.*;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.*;


// ADD DEEP COMMENTING AND ALSO TAKE SOME NOTES ABOUT NLP AND THE SOLUTION TO THE ISSUE PRIOR WOULD BE USEFUL FOR LATER CODE

public class NLPProcessor {
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Initialize the Stanford NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> AnalyzeAndMatch(String CvText, List<String> JobRequirements) {
        // Create a CoreDocument with the CV text
        CoreDocument doc = new CoreDocument(CvText);
        pipeline.annotate(doc);  // Analyze the text

        // Set to store extracted words and noun phrases
        Set<String> ExtractedWords = new HashSet<>();
        Set<String> ExtractedPhrases = new HashSet<>();

        // Extract tokens (words) from the CV
        for (CoreLabel token : doc.tokens()) {
            ExtractedWords.add(token.word().toLowerCase());
        }

        // Extract noun phrases from the CV using dependency parsing
        for (CoreSentence sentence : doc.sentences()) {
            for (CoreLabel token : sentence.tokens()) {
                // Capture sequences of noun phrases (simplified for now)
                if (token.tag().startsWith("NN")) {
                    ExtractedPhrases.add(token.word().toLowerCase());
                }
            }
        }

        // Debugging output
        System.out.println("Extracted Noun Phrases: " + ExtractedPhrases);
        System.out.println("Extracted Words: " + ExtractedWords);

        // Matching job requirements with phrases first
        List<String> MatchedRequirements = new ArrayList<>();
        LevenshteinDistance ld = new LevenshteinDistance();

        // Compare noun phrases first
        for (String Requirement : JobRequirements) {
            String LowerReq = Requirement.toLowerCase();
            for (String Phrase : ExtractedPhrases) {
                if (LowerReq.contains(Phrase)) {
                    MatchedRequirements.add(Requirement);
                    break;  // Stop checking if a match is found
                }
            }
        }

        // If no matches are found in the phrases, compare individual words
        if (MatchedRequirements.isEmpty()) {
            for (String Requirement : JobRequirements) {
                String LowerReq = Requirement.toLowerCase();
                for (String Word : ExtractedWords) {
                    int Distance = ld.apply(Word, LowerReq);
                    if (Distance <= 2) {
                        MatchedRequirements.add(Requirement);
                        break;  // Stop checking if a match is found
                    }
                }
            }
        }

        // Debugging output
        System.out.println("Matched Job Requirements: " + MatchedRequirements);
        return MatchedRequirements;
    }
}

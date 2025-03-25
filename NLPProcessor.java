import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import java.util.*;
import org.apache.commons.text.similarity.LevenshteinDistance;


public class NLPProcessor {
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Load the NLP pipeline with English models
        Properties props = new Properties();
        /*
            -tokenize - breaks text into words
            -ssplit - splits up sentences
            -pos - tags parts of speech like nouns, verbs, etc.
            -lemma - converts words to their base form (like running to run or skipping to skip to make it easier for NLP)
            -ner - Named Entity Recognition (NER) - detects names, places, skills, etc.
            -parse - Analyzes sentence structure
            -depparse - Dependency parsing to understand sentence structure better
        */
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> AnalyzeAndMatch(String CvText, List<String> JobRequirements) {
        // Creates a CoreDocument with CvText and runs the NLP analysis on the text
        CoreDocument doc = new CoreDocument(CvText);
        pipeline.annotate(doc); // Ensure fresh analysis each time

        // Store extracted words for matching
        Set<String> ExtractedWords = new HashSet<>();

        // Extract Named Entities (Skills, Organizations, Locations, People, etc.)
        for (CoreEntityMention em : doc.entityMentions()) {
            // Stores the extracted words in a HashSet to prevent duplicates
            ExtractedWords.add(em.text().toLowerCase());
        }

        // Extract Lemmatized Words to Handle Word Variations
        for (CoreLabel token : doc.tokens()) {
            String lemma = token.lemma().toLowerCase();
            ExtractedWords.add(lemma);
        }

        List<String> MatchedRequirements = new ArrayList<>();
        LevenshteinDistance ld = new LevenshteinDistance();

        // Compare extracted words with job requirements
        for (String Requirement : JobRequirements) {
            String LowerReq = Requirement.toLowerCase();

            for (String Word : ExtractedWords) {
                // Calculate Levenshtein Distance
                int Distance = ld.apply(Word, LowerReq);

                // Allow a small difference
                if (Distance <= 2) {
                    MatchedRequirements.add(Requirement);
                    // Stop checking if a match is found to avoid duplicates
                    break;
                }
            }
        }

        // Debugging Output: Show Matched Requirements
        System.out.println("Matched Job Requirements: " + MatchedRequirements);

        return MatchedRequirements;
    }
}



/*
i have used and downloaded the Stanford nlp and using a LevenshteinDistance.
 */
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import java.util.*;

public class NLPProcessor {
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Load the NLP pipeline with English models
        Properties props = new Properties();
        /*
            -tokenize - breaks text into words
            -ssplit - splits up sentences
            -pos - tags parts of speechs like nouns verbs etc etc
            -lemma - converts words to their base form (like running to run or skipping to skip to make the easier for the nlp)
            -ner - Named Enitiy Recongition (NER) - detects names places skills etc etc
            -parse - Analyse sentences structure

        */
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
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

        // Compare extracted words with job requirements
        for (String Requirement : JobRequirements) {
            String lowerReq = Requirement.toLowerCase();

            for (String word : ExtractedWords) {
                // STRICT MATCH ONLY to prevent false positives
                if (word.equals(lowerReq)) {
                    MatchedRequirements.add(Requirement);
                    break;
                }
            }
        }

        // Debugging Output: Show Matched Requirements
        System.out.println("Matched Job Requirements: " + MatchedRequirements);

        return MatchedRequirements;
    }
}


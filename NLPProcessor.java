import edu.stanford.nlp.pipeline.*;

import java.util.*;

public class NLPProcessor {
    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Load the NLP pipeline with English models
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public List<String> AnalyzeAndMatch(String CvText, List<String> JobRequirements) {
        CoreDocument doc = new CoreDocument(CvText);
        pipeline.annotate(doc);

        // Store extracted words for matching
        Set<String> ExtractedWords = new HashSet<>();

        // Extract Named Entities (Skills, Organizations, Locations, People, etc.)
        for (CoreEntityMention em : doc.entityMentions()) {
            ExtractedWords.add(em.text().toLowerCase()); // Store in lowercase for comparison
        }

        // Compare extracted words with job requirements
        List<String> MatchedRequirements = new ArrayList<>();
        for (String Requirement : JobRequirements) {
            if (ExtractedWords.contains(Requirement.toLowerCase())) {
                MatchedRequirements.add(Requirement);
            }
        }

        return MatchedRequirements; // Return matched requirements
    }
}


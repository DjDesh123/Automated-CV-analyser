import edu.stanford.nlp.pipeline.*;

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
        // CoreDocuments with CvText and then uses pipeline to runs the NLP analyse on the text
        CoreDocument doc = new CoreDocument(CvText);
        pipeline.annotate(doc);

        // Store extracted words for matching
        Set<String> ExtractedWords = new HashSet<>();

        // Extract Named Entities (Skills, Organizations, Locations, People, etc.)
        // the for loop basicallys says for each entitiy that is menetioned
        for (CoreEntityMention em : doc.entityMentions()) {
            //Stores the Extracted words and  stores them into a hashset to prevent duplicates
            ExtractedWords.add(em.text().toLowerCase());
        }


        List<String> MatchedRequirements = new ArrayList<>();
        // Compare extracted words with job requirements
        for (String Requirement : JobRequirements) {
            if (ExtractedWords.contains(Requirement.toLowerCase())) {
                MatchedRequirements.add(Requirement);
            }
        }

        // Return matched requirements
        return MatchedRequirements;
    }
}

